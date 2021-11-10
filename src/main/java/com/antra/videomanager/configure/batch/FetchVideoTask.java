package com.antra.videomanager.configure.batch;

import com.antra.videomanager.utils.PropertyUtil;
import com.antra.videomanager.utils.YoutubeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FetchVideoTask implements Tasklet {

    private final String propertyName = "playlist.properties";
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private ExecutorService pool;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        List<CompletableFuture<Set<String>>> futures = Stream.of(PropertyUtil.fetchProperties(propertyName).getProperty("id").split(","))
                .map(id -> CompletableFuture.supplyAsync(() -> {
                            try {
                                return YoutubeUtil.getVideoIdFromPlayListItem(id);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex.getMessage());
                            }
                        }, pool)
                ).collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        Set<Object> ids = futures.stream()
                .map(f -> f.join())
                .flatMap(s -> Stream.of(s))
                .collect(Collectors.toSet());
        logger.info("Load videos from playlists {}", ids);
        return RepeatStatus.FINISHED;
    }


    public static void main(String[] args) {
        List<Integer> list = Optional.ofNullable(Arrays.asList(1, 2, 3)).orElseThrow(RuntimeException::new);
        System.out.println(list);
    }
}

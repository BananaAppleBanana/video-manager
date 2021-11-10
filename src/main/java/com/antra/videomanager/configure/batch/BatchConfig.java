package com.antra.videomanager.configure.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Tasklet FetchVideoTask;

    @Bean
    public Step stepOne(){
        return steps.get("stepOne")
                .tasklet(FetchVideoTask)
                .build();
    }

    @Bean
    public Step stepTwo(){
        return steps.get("stepTwo")
                .tasklet(new FetchVideoJob2())
                .build();
    }

    @Bean
    public Job demoJob(){
        return jobs.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .next(stepTwo())
                .build();
    }

    @Scheduled(fixedDelay=60*60*5000)
    public void perform() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        //jobLauncher.run(demoJob(), params);
    }


    //@Autowired(required = false)
    public void setDataSource(DataSource dataSource) {
        //don't add datasource here.
        //batch configuration will choose in-memory map as meta-data storage instead of datasource if datasource is null
    }

    private static class FetchVideoJob2 implements Tasklet {

        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
        {
            System.out.println("MyTaskTWO start..");

            // ... your code

            System.out.println("MyTaskTWO done..");
            return RepeatStatus.FINISHED;
        }
    }
}

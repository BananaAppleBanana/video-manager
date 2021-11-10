package com.antra.videomanager.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService pool() {
        return new ThreadPoolExecutor(10, 30, 5L, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    }
}

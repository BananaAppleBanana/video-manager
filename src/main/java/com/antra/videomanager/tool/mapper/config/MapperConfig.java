package com.antra.videomanager.tool.mapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public MapperProxyListener mapperProxyListener() {
        return new MapperProxyListener();
    }

    @Bean
    public MapperProxyBeanFactory mapperProxyBeanFactory() {
        return new MapperProxyBeanFactory();
    }
}

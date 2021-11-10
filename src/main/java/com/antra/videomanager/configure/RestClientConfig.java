package com.antra.videomanager.configure;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate tokenRequestRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .build();
    }
}

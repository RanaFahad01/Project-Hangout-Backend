package com.ranafahad.projecthangoutbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    //RestTemplate bean needed for spring web
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

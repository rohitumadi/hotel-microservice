package com.hotel.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
    @Bean
    @LoadBalanced//This will use the service name while calling other services
    //instead of hardcoding host and port
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

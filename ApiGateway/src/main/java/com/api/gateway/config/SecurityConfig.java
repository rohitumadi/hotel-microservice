package com.api.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        httpSecurity
                .authorizeExchange(req->req
                        .anyExchange()
                        .authenticated())
                .oauth2Client(Customizer.withDefaults())
                .oauth2ResourceServer(resourceServer->
                        resourceServer.jwt(Customizer.withDefaults()));


        return httpSecurity.build();
    }

}

package com.hotel.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient annotation is not required anymore.
//		Simply adding spring-cloud-starter-netflix-eureka-client
//		to dependencies will enable the client
@EnableFeignClients
public class UserServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}



}

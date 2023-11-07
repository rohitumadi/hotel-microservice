package com.hotel.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient annotation is not required anymore.
//		Simply adding spring-cloud-starter-netflix-eureka-client
//		to dependencies will enable the client
public class UserServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}



}

package com.wipro.ORS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsdOrsCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsdOrsCartServiceApplication.class, args);
		System.out.println("######### Cart microservice has satrted ####################");
	}

}

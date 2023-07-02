package com.wipro.ORS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsdOrsInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsdOrsInventoryServiceApplication.class, args);
		System.out.println("######### Cart microservice has satrted ####################");
	}

}

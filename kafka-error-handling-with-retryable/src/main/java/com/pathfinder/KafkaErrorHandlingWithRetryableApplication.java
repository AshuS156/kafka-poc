package com.pathfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaErrorHandlingWithRetryableApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaErrorHandlingWithRetryableApplication.class, args);
	}

}

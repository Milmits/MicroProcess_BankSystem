package com.microservice_bank.microservice_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class microservice_bankApplication {

	public static void main(String[] args) {
		SpringApplication.run(microservice_bankApplication.class, args);
	}

}

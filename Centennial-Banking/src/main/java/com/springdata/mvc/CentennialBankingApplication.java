package com.springdata.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CentennialBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentennialBankingApplication.class, args);
		System.out.println("Spring Boot MVC JPA FinTech app is Running!");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
package com.sender.scalene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScaleneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScaleneApplication.class, args);
	}

	@Bean
	public CorsConfiguration corsConfig() {
		return new CorsConfiguration();
	}

}

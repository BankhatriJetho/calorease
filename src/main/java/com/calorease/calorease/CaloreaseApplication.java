package com.calorease.calorease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.calorease")
public class CaloreaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaloreaseApplication.class, args);
	}

}

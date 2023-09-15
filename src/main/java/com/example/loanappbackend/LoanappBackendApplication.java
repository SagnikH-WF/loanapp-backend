package com.example.loanappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class LoanappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanappBackendApplication.class, args);
		System.out.println("Hello Spring Boot");
	}

}

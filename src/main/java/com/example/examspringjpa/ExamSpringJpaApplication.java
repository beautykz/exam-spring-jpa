package com.example.examspringjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExamSpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamSpringJpaApplication.class, args);
	}

}

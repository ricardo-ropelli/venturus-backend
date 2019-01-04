package com.venturus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.venturus.repository")
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


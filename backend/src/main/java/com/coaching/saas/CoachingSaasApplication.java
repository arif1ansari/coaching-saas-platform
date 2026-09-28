package com.coaching.saas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoachingSaasApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoachingSaasApplication.class, args);
    }
}

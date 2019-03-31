package com.github.ryneal.postcodegeolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PostcodeGeolocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostcodeGeolocationApplication.class, args);
    }

}

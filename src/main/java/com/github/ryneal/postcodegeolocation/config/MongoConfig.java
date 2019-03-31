package com.github.ryneal.postcodegeolocation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.github.ryneal.postcodegeolocation.repository")
public class MongoConfig {

}

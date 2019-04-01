package com.github.ryneal.postcodegeolocation.config;

import com.mongodb.MongoClient;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConditionalOnProperty(name = "scheduling.enabled", havingValue = "true")
@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT2H")
public class SchedulingConfig {

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabaseName;

    private MongoClient mongoClient;

    public SchedulingConfig(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Bean
    public LockProvider lockProvider() {
        return new MongoLockProvider(this.mongoClient, this.mongoDatabaseName);
    }
}

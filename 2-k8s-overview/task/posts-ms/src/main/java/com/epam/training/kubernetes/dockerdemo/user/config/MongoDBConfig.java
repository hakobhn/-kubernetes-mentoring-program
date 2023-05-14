package com.epam.training.kubernetes.dockerdemo.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.epam.training.kubernetes.dockerdemo.user.domain.repository")
@Configuration
public class MongoDBConfig {

}

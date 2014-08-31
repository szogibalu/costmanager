package com.szogi.costmanager.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.szogi.costmanager.core.repository")
@PropertySource("classpath:config/application.properties")
public class ApplicationConfiguration {

}
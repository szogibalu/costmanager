package com.szogi.costmanager.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MongoConfiguration.class)
public class ServicesConfiguration {
}

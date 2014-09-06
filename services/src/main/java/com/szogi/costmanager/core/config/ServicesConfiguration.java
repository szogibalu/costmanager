package com.szogi.costmanager.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MongoConfiguration.class)
public class ServicesConfiguration {
}

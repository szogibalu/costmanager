package com.szogi.costmanager.services.config;

import com.szogi.costmanager.data.services.config.MongoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:${cm.config.dir}/services.properties")
@Import(MongoConfiguration.class)
class CostManagerWebServicesConfiguration {
}

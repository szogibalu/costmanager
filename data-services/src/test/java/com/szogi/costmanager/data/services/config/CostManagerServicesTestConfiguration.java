package com.szogi.costmanager.data.services.config;

import com.szogi.costmanager.core.mongo.MongoDbHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/services.properties")
@Import({MongoConfiguration.class})
public class CostManagerServicesTestConfiguration {

    @Bean
    public MongoDbHelper mongoDbHelper() {
        return new MongoDbHelper();
    }
}

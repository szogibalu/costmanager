package com.szogi.costmanager.core.config;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.szogi.costmanager.core.repository.ExtendedCostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "cm_db";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.szogi.costmanager.core.model";
    }

    @Bean
    protected ExtendedCostRepository extendedCostRepository() throws Exception {
        return new ExtendedCostRepository(mongoTemplate());
    }
}

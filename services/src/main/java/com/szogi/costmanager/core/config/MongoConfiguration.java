package com.szogi.costmanager.core.config;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.szogi.costmanager.core.repository.ExtendedCostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.szogi.costmanager.core.repository")
class MongoConfiguration extends AbstractMongoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoConfiguration.class);

    @Autowired
    private Environment environment;

    @Override
    public String getDatabaseName() {
        String dbName = environment.getRequiredProperty("db.name");
        LOGGER.info("Mongo database Name: {}", dbName);
        return dbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        String dbHost = environment.getRequiredProperty("db.host");
        Integer dbPort = environment.getRequiredProperty("db.port", Integer.class);
        LOGGER.info("Create MongoClient for: {}:{}", dbHost, dbPort);
        return new MongoClient(dbHost, dbPort);
    }

    @Override
    public UserCredentials getUserCredentials() {
        return new UserCredentials(environment.getRequiredProperty("db.user"), environment.getRequiredProperty("db.password"));
    }

    @Bean
    public ExtendedCostRepository extendedCostRepository() throws Exception {
        return new ExtendedCostRepository(mongoTemplate());
    }
}

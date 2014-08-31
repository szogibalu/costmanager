package com.szogi.costmanager.core.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;

@Configuration
@Profile("test")
public class MongoTestConfiguration extends MongoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoTestConfiguration.class);

    @Autowired
    private Environment environment;

    @Override
    protected String getDatabaseName() {
        return environment.getRequiredProperty("db.name");
    }

    @Override
    public Mongo mongo() throws Exception {
        LOGGER.info("Create MongoClient for Testing...");
        return new MongoClient(environment.getRequiredProperty("db.host"), environment.getRequiredProperty("db.port", Integer.class));
    }

    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(environment.getRequiredProperty("db.user"), environment.getRequiredProperty("db.password"));
    }
}

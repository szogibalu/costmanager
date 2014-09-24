package com.szogi.costmanager.core.mongo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDbHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbHelper.class);
    @Autowired
    protected MongoOperations operations;

    public void createCollection(Class<?> collection) {
        createCollection(collection, CreateOption.KEEP_EXISTING);
    }

    public void createCollection(Class<?> collection, CreateOption option) {
        boolean alreadyExists = false;
        if (operations.collectionExists(collection)) {
            LOGGER.warn("Collection already exists: {}", collection);
            if (CreateOption.DROP_EXISTING == option) {
                dropCollection(collection);
            } else {
                alreadyExists = true;
            }
        }

        if (!alreadyExists) {
            LOGGER.info("Create collection: {}", collection);
            operations.createCollection(collection);
        }
    }

    public void dropCollection(Class<?> collection) {
        if (operations.collectionExists(collection)) {
            LOGGER.info("Drop collection: {}", collection);
            operations.dropCollection(collection);
        } else {
            LOGGER.warn("Collection does not exist: {}", collection);
        }
    }

    public enum CreateOption {
        DROP_EXISTING, KEEP_EXISTING;
    }
}

package com.szogi.costmanager.core.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractMongoDbDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMongoDbDao.class);

    @Autowired
    protected MongoOperations operations;

    protected abstract Class<?> getCollection();

    public void createCollection() {
        createCollection(false);
    }

    public void createCollection(boolean drop) {
        boolean alreadyExists = false;
        if (operations.collectionExists(getCollection())) {
            LOGGER.warn("Collection already exists: {}", getCollection());
            if (drop) {
                dropCollection();
            } else {
                alreadyExists = true;
            }
        }

        if (!alreadyExists) {
            LOGGER.info("Create collection: {}", getCollection());
            operations.createCollection(getCollection());
        }
    }

    public void dropCollection() {
        if (operations.collectionExists(getCollection())) {
            LOGGER.info("Drop collection: {}", getCollection());
            operations.dropCollection(getCollection());
        } else {
            LOGGER.warn("Collection does not exist: {}", getCollection());
        }
    }
}

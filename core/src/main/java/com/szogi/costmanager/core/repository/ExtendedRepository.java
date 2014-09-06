package com.szogi.costmanager.core.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public abstract class ExtendedRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedRepository.class);

    private final MongoOperations operations;

    public ExtendedRepository(MongoOperations operations) {
        this.operations = operations;
    }

    protected abstract Class<?> getCollection();

    public void createCollection() {
        if (!operations.collectionExists(getCollection())) {
            LOGGER.info("Create collection: {}", getCollection());
            operations.createCollection(getCollection());
        } else {
            LOGGER.warn("Collection already exists: {}", getCollection());
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

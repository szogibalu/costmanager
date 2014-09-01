package com.szogi.costmanager.core.repository;

import com.szogi.costmanager.core.model.Cost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import static com.szogi.costmanager.core.model.Cost.COLLECTION_NAME;

@Repository
public class ExtendedCostRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedCostRepository.class);

    private final MongoOperations operations;

    public ExtendedCostRepository(MongoOperations operations) {
        this.operations = operations;
    }

    public void createCollection() {
        if (!operations.collectionExists(Cost.class)) {
            LOGGER.info("Create collection: {}", COLLECTION_NAME);
            operations.createCollection(Cost.class);
        } else {
            LOGGER.warn("Collection already exists: {}", COLLECTION_NAME);
        }
    }

    public void dropCollection() {
        if (operations.collectionExists(Cost.class)) {
            LOGGER.info("Drop collection: {}", Cost.class);
            operations.dropCollection(Cost.class);
        } else {
            LOGGER.warn("Collection does not exist: {}", COLLECTION_NAME);
        }
    }
}

package com.szogi.costmanager.services.repository;

import com.szogi.costmanager.core.repository.ExtendedRepository;
import com.szogi.costmanager.services.model.Cost;
import org.springframework.data.mongodb.core.MongoOperations;


public class CostExtendedRepository extends ExtendedRepository {

    public CostExtendedRepository(MongoOperations operations) {
        super(operations);
    }

    @Override
    public Class<?> getCollection() {
        return Cost.class;
    }
}

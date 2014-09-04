package com.szogi.costmanager.core.repository;

import com.szogi.costmanager.core.model.Cost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CostRepository extends MongoRepository<Cost, String> {
}

package com.szogi.costmanager.services.repository;

import com.szogi.costmanager.services.model.CostObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CostObjectRepository extends MongoRepository<CostObject, String> {
}

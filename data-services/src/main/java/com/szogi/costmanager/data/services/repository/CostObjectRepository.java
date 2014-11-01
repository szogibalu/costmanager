package com.szogi.costmanager.data.services.repository;

import com.szogi.costmanager.data.services.model.CostObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CostObjectRepository extends MongoRepository<CostObject, String> {
}

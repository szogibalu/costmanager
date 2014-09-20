package com.szogi.costmanager.services.dao;

import com.szogi.costmanager.services.model.CostObject;
import org.springframework.data.mongodb.repository.MongoRepository;

interface CostObjectRepository extends MongoRepository<CostObject, String> {
}

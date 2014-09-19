package com.szogi.costmanager.services.dao;

import com.szogi.costmanager.services.model.Cost;
import org.springframework.data.mongodb.repository.MongoRepository;

interface CostRepository extends MongoRepository<Cost, String> {
}

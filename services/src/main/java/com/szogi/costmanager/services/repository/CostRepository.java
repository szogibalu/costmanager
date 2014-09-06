package com.szogi.costmanager.services.repository;


import com.szogi.costmanager.services.model.Cost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CostRepository extends MongoRepository<Cost, String> {
}

package com.szogi.costmanager.services.repository;

import com.szogi.costmanager.services.model.TagObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagObjectRepository extends MongoRepository<TagObject, String> {
}

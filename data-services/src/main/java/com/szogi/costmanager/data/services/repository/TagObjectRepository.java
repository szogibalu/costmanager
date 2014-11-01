package com.szogi.costmanager.data.services.repository;

import com.szogi.costmanager.data.services.model.TagObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagObjectRepository extends MongoRepository<TagObject, String> {
}

package com.szogi.costmanager.services.dao;

import com.szogi.costmanager.services.model.TagObject;
import org.springframework.data.mongodb.repository.MongoRepository;

interface TagObjectRepository extends MongoRepository<TagObject, String> {
}

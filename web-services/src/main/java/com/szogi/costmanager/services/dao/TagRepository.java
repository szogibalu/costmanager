package com.szogi.costmanager.services.dao;

import com.szogi.costmanager.services.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

interface TagRepository extends MongoRepository<Tag, String> {
}

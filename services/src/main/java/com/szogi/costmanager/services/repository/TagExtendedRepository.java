package com.szogi.costmanager.services.repository;

import com.szogi.costmanager.core.repository.ExtendedRepository;
import com.szogi.costmanager.services.model.Tag;

public class TagExtendedRepository extends ExtendedRepository {

    @Override
    protected Class<?> getCollection() {
        return Tag.class;
    }
}

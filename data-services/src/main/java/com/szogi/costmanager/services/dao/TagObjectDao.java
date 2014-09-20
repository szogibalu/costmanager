package com.szogi.costmanager.services.dao;

import com.szogi.costmanager.core.dao.AbstractMongoDbDao;
import com.szogi.costmanager.services.model.TagObject;

public class TagObjectDao extends AbstractMongoDbDao {

    @Override
    protected Class<?> getCollection() {
        return TagObject.class;
    }
}

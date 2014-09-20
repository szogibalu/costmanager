package com.szogi.costmanager.services.dao;

import com.szogi.costmanager.core.dao.AbstractMongoDbDao;
import com.szogi.costmanager.services.model.Tag;

public class TagDao extends AbstractMongoDbDao {

    @Override
    protected Class<?> getCollection() {
        return Tag.class;
    }
}

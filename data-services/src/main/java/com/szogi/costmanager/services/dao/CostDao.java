package com.szogi.costmanager.services.dao;

import com.szogi.costmanager.core.dao.AbstractMongoDbDao;
import com.szogi.costmanager.services.model.Cost;
import com.szogi.costmanager.services.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;


public class CostDao extends AbstractMongoDbDao {

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Class<?> getCollection() {
        return Cost.class;
    }

    public Cost save(Cost cost) {
        checkNotNull(cost);
        if (isNotEmpty(cost.getTags())) {
            for (Tag tag : cost.getTags()) {
                if (tag.getId() == null) {
                    Tag savedTag = tagRepository.save(tag);
                    tag.setId(savedTag.getId());
                }
            }
        }
        return costRepository.save(cost);
    }

    public Cost findOne(String id) {
        return costRepository.findOne(id);
    }

    public List<Cost> findAll() {
        return costRepository.findAll();
    }
}

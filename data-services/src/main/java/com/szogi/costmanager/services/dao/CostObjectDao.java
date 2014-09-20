package com.szogi.costmanager.services.dao;

import com.szogi.costmanager.core.dao.AbstractMongoDbDao;
import com.szogi.costmanager.services.model.CostObject;
import com.szogi.costmanager.services.model.TagObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;


public class CostObjectDao extends AbstractMongoDbDao {

    @Autowired
    private CostObjectRepository costObjectRepository;

    @Autowired
    private TagObjectRepository tagObjectRepository;

    @Override
    public Class<?> getCollection() {
        return CostObject.class;
    }

    public CostObject save(CostObject costObject) {
        checkNotNull(costObject);
        if (isNotEmpty(costObject.getTagObjects())) {
            for (TagObject tagObject : costObject.getTagObjects()) {
                if (tagObject.getId() == null) {
                    TagObject savedTagObject = tagObjectRepository.save(tagObject);
                    tagObject.setId(savedTagObject.getId());
                }
            }
        }
        return costObjectRepository.save(costObject);
    }

    public CostObject findOne(String id) {
        return costObjectRepository.findOne(id);
    }

    public List<CostObject> findAll() {
        return costObjectRepository.findAll();
    }
}

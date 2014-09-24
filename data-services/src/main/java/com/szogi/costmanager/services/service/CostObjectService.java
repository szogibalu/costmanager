package com.szogi.costmanager.services.service;

import com.szogi.costmanager.services.model.CostObject;
import com.szogi.costmanager.services.model.TagObject;
import com.szogi.costmanager.services.repository.CostObjectRepository;
import com.szogi.costmanager.services.repository.TagObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class CostObjectService {

    @Autowired
    private CostObjectRepository costObjectRepository;

    @Autowired
    private TagObjectRepository tagObjectRepository;

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

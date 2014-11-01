package com.szogi.costmanager.data.services.service;

import com.szogi.costmanager.data.services.model.CostObject;
import com.szogi.costmanager.data.services.model.TagObject;
import com.szogi.costmanager.data.services.repository.CostObjectRepository;
import com.szogi.costmanager.data.services.repository.TagObjectRepository;
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
        if (isNotEmpty(costObject.getTags())) {
            for (TagObject tagObject : costObject.getTags()) {
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

package com.szogi.costmanager.services.repository.listener;

import com.szogi.costmanager.services.model.CostObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

public class CostMongoEventListener extends AbstractMongoEventListener<CostObject> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CostMongoEventListener.class);
}

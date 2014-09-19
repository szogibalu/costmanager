package com.szogi.costmanager.services.dao.listener;

import com.szogi.costmanager.services.model.Cost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

public class CostMongoEventListener extends AbstractMongoEventListener<Cost> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CostMongoEventListener.class);
}

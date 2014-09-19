package com.szogi.costmanager.services.dao.listener;

import com.mongodb.DBObject;
import com.szogi.costmanager.services.model.Cost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

public class CostMongoEventListener extends AbstractMongoEventListener<Cost> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CostMongoEventListener.class);

    @Override
    public void onAfterSave(Cost source, DBObject dbo) {
        LOGGER.info("Cost saved: {}", source);
        super.onAfterSave(source, dbo);
    }
}

package com.szogi.costmanager.data.services.repository.listener;

import com.mongodb.DBObject;
import com.szogi.costmanager.data.services.model.CostObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

public class CostMongoEventListener extends AbstractMongoEventListener<CostObject> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CostMongoEventListener.class);


    @Override
    public void onAfterSave(CostObject source, DBObject dbo) {
        super.onAfterSave(source, dbo);
    }
}

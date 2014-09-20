package com.szogi.costmanager.services.util;


import com.szogi.costmanager.services.model.CostObject;
import com.szogi.costmanager.services.model.TagObject;

import java.math.BigDecimal;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public final class TestObjectFactory {

    private TestObjectFactory() {
    }

    public static CostObject testCost() {
        return testCost(testTag());
    }

    public static CostObject testCost(TagObject tagObject) {
        return new CostObject.Builder().setDescription(randomAlphabetic(15)).setAmount(BigDecimal.ONE).addTag(tagObject).build();
    }

    public static CostObject testCostWithoutAnyTag() {
        return new CostObject.Builder().setDescription(randomAlphabetic(15)).setAmount(BigDecimal.TEN).build();
    }

    public static TagObject testTag() {
        return new TagObject(randomAlphabetic(10));
    }
}

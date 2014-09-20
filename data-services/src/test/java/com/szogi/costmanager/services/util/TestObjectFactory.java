package com.szogi.costmanager.services.util;


import com.szogi.costmanager.services.model.Cost;
import com.szogi.costmanager.services.model.Tag;

import java.math.BigDecimal;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public final class TestObjectFactory {

    private TestObjectFactory() {
    }

    public static Cost testCost() {
        return testCost(testTag());
    }

    public static Cost testCost(Tag tag) {
        return new Cost.Builder().setDescription(randomAlphabetic(15)).setAmount(BigDecimal.ONE).addTag(tag).build();
    }

    public static Cost testCostWithoutAnyTag() {
        return new Cost.Builder().setDescription(randomAlphabetic(15)).setAmount(BigDecimal.TEN).build();
    }

    public static Tag testTag() {
        return new Tag(randomAlphabetic(10));
    }
}

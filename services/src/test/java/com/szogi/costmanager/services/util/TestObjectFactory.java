package com.szogi.costmanager.services.util;


import com.szogi.costmanager.services.model.Cost;
import com.szogi.costmanager.services.model.Tag;

import java.math.BigDecimal;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public final class TestObjectFactory {

    private TestObjectFactory() {
    }

    public static Cost testCost() {
        return new Cost.Builder().setDescription(randomAlphabetic(15)).setAmount(BigDecimal.ONE).addTag(new Tag(randomAlphabetic(10))).build();
    }
}

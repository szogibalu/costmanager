package com.szogi.costmanager.services.util;


import com.szogi.costmanager.services.model.Cost;

import java.math.BigDecimal;

public final class TestObjectFactory {

    private TestObjectFactory() {
    }

    public static Cost testCost() {
        return new Cost.Builder().setDescription("test").setAmount(BigDecimal.ONE).build();
    }
}

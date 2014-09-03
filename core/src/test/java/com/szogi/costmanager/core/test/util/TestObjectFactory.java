package com.szogi.costmanager.core.test.util;


import com.szogi.costmanager.core.model.Cost;

import java.math.BigDecimal;

public final class TestObjectFactory {

    private TestObjectFactory() {
    }

    public static Cost testCost() {
        return new Cost.Builder().setDescription("test").setValue(BigDecimal.ONE).build();
    }

}

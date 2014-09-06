package com.szogi.costmanager.services.repository;


import com.szogi.costmanager.services.SpringIntegrationTest;
import com.szogi.costmanager.services.model.Cost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.szogi.costmanager.services.util.TestObjectFactory.testCost;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CostRepositoryIntegrationTest extends SpringIntegrationTest {

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private ExtendedCostRepository extendedCostRepository;

    @Test
    public void save() {
        Cost savedCost = costRepository.save(testCost());
        Cost loadedCost = costRepository.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
    }

    @Before
    public void setUp() throws Exception {
        extendedCostRepository.createCollection();
    }

    @Before
    @After
    public void clear() throws Exception {
        extendedCostRepository.dropCollection();

    }
}

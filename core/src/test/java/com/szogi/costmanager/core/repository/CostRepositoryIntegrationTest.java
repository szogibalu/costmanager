package com.szogi.costmanager.core.repository;


import com.szogi.costmanager.core.config.ApplicationConfiguration;
import com.szogi.costmanager.core.model.Cost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.szogi.costmanager.core.test.util.TestObjectFactory.testCost;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@ActiveProfiles("test")
public class CostRepositoryIntegrationTest {

    @Autowired
    CostRepository costRepository;

    @Autowired
    ExtendedCostRepository extendedCostRepository;

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

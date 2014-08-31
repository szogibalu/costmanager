package com.szogi.costmanager.core.repository;


import com.szogi.costmanager.core.config.ApplicationConfiguration;
import com.szogi.costmanager.core.config.MongoTestConfiguration;
import com.szogi.costmanager.core.model.Cost;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, MongoTestConfiguration.class})
@ActiveProfiles("test")
public class CostRepositoryIntegrationTest {

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private ExtendedCostRepository extendedCostRepository;

    private static Cost getCost() {
        String description = RandomStringUtils.randomAlphabetic(10);
        BigDecimal value = BigDecimal.valueOf(RandomUtils.nextDouble(1, 100));
        return new Cost.Builder().setDescription(description).setValue(value).build();
    }

    @Test
    public void save() {
        Cost savedCost = costRepository.save(getCost());
        Cost loadedCost = costRepository.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
    }

    @Before
    public void setUp() throws Exception {
        extendedCostRepository.createCollection();
    }

    @After
    public void tearDown() throws Exception {
        costRepository.deleteAll();
        extendedCostRepository.dropCollection();
    }
}

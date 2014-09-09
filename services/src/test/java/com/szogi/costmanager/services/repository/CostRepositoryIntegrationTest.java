package com.szogi.costmanager.services.repository;


import com.szogi.costmanager.services.config.CostManagerServicesTestConfiguration;
import com.szogi.costmanager.services.config.LocalMongoDb;
import com.szogi.costmanager.services.model.Cost;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.szogi.costmanager.services.util.TestObjectFactory.testCost;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CostManagerServicesTestConfiguration.class})
public class CostRepositoryIntegrationTest {

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private CostExtendedRepository costExtendedRepository;

    @BeforeClass
    public static void init() throws Exception {
        LocalMongoDb.start();
    }

    @Test
    public void save() {
        Cost savedCost = costRepository.save(testCost());
        Cost loadedCost = costRepository.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
        assertThat(loadedCost.getTags(), is(not(empty())));
    }

    @Before
    public void setUp() throws Exception {
        costExtendedRepository.createCollection(true);
    }

    @After
    public void clear() throws Exception {
        costExtendedRepository.dropCollection();
    }
}

package com.szogi.costmanager.core.service;

import com.google.common.collect.Lists;
import com.szogi.costmanager.core.model.Cost;
import com.szogi.costmanager.core.model.CostList;
import com.szogi.costmanager.core.repository.CostRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static com.szogi.costmanager.core.test.util.TestObjectFactory.testCost;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class CostServiceTest {

    CostService target;

    @Mock
    CostRepository mockedCostRepository;

    @Before
    public void setUp() throws Exception {
        target = new CostService(mockedCostRepository);
    }

    @Test
    public void save() {
        Response response = target.saveCost(testCost());
        verify(mockedCostRepository).save(testCost());
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void loadAll() {
        Mockito.when(mockedCostRepository.findAll()).thenReturn(Lists.<Cost>newArrayList());
        CostList costList = target.loadCosts();
        assertThat(costList, is(notNullValue()));

    }
}
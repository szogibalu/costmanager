package com.szogi.costmanager.services.service;

import com.google.common.collect.Lists;
import com.szogi.costmanager.services.dao.CostDao;
import com.szogi.costmanager.services.model.Cost;
import com.szogi.costmanager.services.model.CostList;
import com.szogi.costmanager.services.model.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CostServiceTest {

    @Mock
    CostDao mockedCostDao;

    @InjectMocks
    @Resource
    private CostService target;


    @Test
    public void save() {
        Cost cost = new Cost.Builder()
                .setDescription(randomAlphabetic(15))
                .setAmount(BigDecimal.ONE)
                .addTag(new Tag(randomAlphabetic(10)))
                .build();
        Response response = target.saveCost(cost);
        verify(mockedCostDao).save(cost);
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void loadAll() {
        Mockito.when(mockedCostDao.findAll()).thenReturn(Lists.<Cost>newArrayList());
        CostList costList = target.loadAll();
        assertThat(costList, is(notNullValue()));
    }
}
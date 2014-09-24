package com.szogi.costmanager.services.service;

import com.google.common.collect.Lists;
import com.szogi.costmanager.services.model.CostObject;
import com.szogi.costmanager.services.model.CostObjectList;
import com.szogi.costmanager.services.model.TagObject;
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
public class CostObjectServiceTest {

    @Mock
    CostObjectService mockedCostObjectService;

    @InjectMocks
    @Resource
    private CostService target;


    @Test
    public void save() {
        CostObject costObject = new CostObject.Builder()
                .setDescription(randomAlphabetic(15))
                .setAmount(BigDecimal.ONE)
                .addTag(new TagObject(randomAlphabetic(10)))
                .build();
        Response response = target.saveCost(costObject);
        verify(mockedCostObjectService).save(costObject);
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void loadAll() {
        Mockito.when(mockedCostObjectService.findAll()).thenReturn(Lists.<CostObject>newArrayList());
        CostObjectList costObjectList = target.loadAll();
        assertThat(costObjectList, is(notNullValue()));
    }
}
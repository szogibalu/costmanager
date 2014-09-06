package com.szogi.costmanager.services.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

public class CostList {

    private List<com.szogi.costmanager.services.model.Cost> costs;

    public CostList() {
    }

    public CostList(List<com.szogi.costmanager.services.model.Cost> costs) {
        this.costs = costs;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
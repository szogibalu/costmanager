package com.szogi.costmanager.data.services.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

public class CostObjectList {

    private List<CostObject> costObjects;

    public CostObjectList() {
    }

    public CostObjectList(List<CostObject> costObjects) {
        this.costObjects = costObjects;
    }

    public List<CostObject> getCostObjects() {
        return costObjects;
    }

    public void setCostObjects(List<CostObject> costObjects) {
        this.costObjects = costObjects;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

package com.szogi.costmanager.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Objects;

public class Tag {


    private String id;

    private String description;

    public Tag() {
    }

    public Tag(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Tag) {
            Tag other = (Tag) o;
            return Objects.equals(id, other.id)
                    && Objects.equals(description, other.description);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

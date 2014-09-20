package com.szogi.costmanager.services.model;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "tags")
@TypeAlias("tag")
public class TagObject {

    @Id
    private String id;

    @Indexed(unique = true)
    private String description;

    public TagObject() {
    }

    public TagObject(String description) {
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
        if (o instanceof TagObject) {
            TagObject other = (TagObject) o;
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

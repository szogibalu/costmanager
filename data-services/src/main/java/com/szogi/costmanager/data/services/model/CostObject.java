package com.szogi.costmanager.data.services.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.Validate.notNull;

@Document(collection = "costs")
@TypeAlias("cost")
public class CostObject implements Serializable {

    @Id
    private String id;

    private String description;

    private Date date;

    private BigDecimal amount;

    private String currency;

    @DBRef
    private List<TagObject> tags;

    public CostObject() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<TagObject> getTags() {
        return tags;
    }

    public void setTags(List<TagObject> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CostObject) {
            CostObject other = (CostObject) o;
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

    public static class Builder {

        private final List<TagObject> tagObjects = newArrayList();
        private String description;
        private Date date = new Date();
        private BigDecimal amount;
        private String currency = "HUF";

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder addTag(TagObject tagObject) {
            tagObjects.add(tagObject);
            return this;
        }

        public Builder addTag(TagObject... tagObjects) {
            this.tagObjects.addAll(newArrayList(tagObjects));
            return this;
        }

        public CostObject build() {
            notNull(description);
            notNull(date);
            notNull(amount);
            notNull(currency);

            CostObject costObject = new CostObject();
            costObject.setDescription(description);
            costObject.setAmount(amount);
            costObject.setCurrency(currency);
            costObject.setDate(date);
            costObject.setTags(tagObjects);
            return costObject;
        }
    }
}
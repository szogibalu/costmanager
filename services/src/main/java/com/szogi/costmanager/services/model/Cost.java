package com.szogi.costmanager.services.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
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
public class Cost implements Serializable {

    @Id
    private String id;

    private String description;

    private Date date;

    private BigDecimal amount;

    private String currency;

    private List<Tag> tags;

    public Cost() {
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cost) {
            Cost other = (Cost) o;
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

        private final List<Tag> tags = newArrayList();
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

        public Builder addTag(Tag tag) {
            tags.add(tag);
            return this;
        }

        public Builder addTag(Tag... tags) {
            this.tags.addAll(newArrayList(tags));
            return this;
        }

        public Cost build() {
            notNull(description);
            notNull(date);
            notNull(amount);
            notNull(currency);

            Cost cost = new Cost();
            cost.setDescription(description);
            cost.setAmount(amount);
            cost.setCurrency(currency);
            cost.setDate(date);
            cost.setTags(tags);
            return cost;
        }
    }
}
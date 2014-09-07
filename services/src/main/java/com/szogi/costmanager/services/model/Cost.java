package com.szogi.costmanager.services.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.notNull;

@Document(collection = Cost.COLLECTION_NAME)
@TypeAlias("cost")
public class Cost implements Serializable {

    public static final String COLLECTION_NAME = "costs";

    @Id
    private String id;

    private String description;

    private Date date;

    private BigDecimal amount;

    private String currency;

    public Cost() {
    }

    private Cost(String description, Date date, BigDecimal amount, String currency) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
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

        public Cost build() {
            notNull(description);
            notNull(date);
            notNull(amount);
            notNull(currency);

            return new Cost(description, date, amount, currency);
        }
    }
}
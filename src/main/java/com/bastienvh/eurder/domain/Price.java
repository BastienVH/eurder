package com.bastienvh.eurder.domain;

import com.bastienvh.eurder.domain.item.Currency;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Price {
    @Positive(message = "Price can't be negative")
    private BigDecimal price;
    private Currency currency;

    public Price(BigDecimal price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    protected Price() {
    }

    public Price addPrice(Price price) {
        return new Price(this.price.add(price.price), Currency.EURO);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Price) obj;
        return Objects.equals(this.price, that.price) &&
                Objects.equals(this.currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, currency);
    }

    @Override
    public String toString() {
        return "Price[" +
                "value=" + price + ", " +
                "currency=" + currency + ']';
    }

}

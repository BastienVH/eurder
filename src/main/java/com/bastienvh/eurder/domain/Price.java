package com.bastienvh.eurder.domain;

import com.bastienvh.eurder.domain.item.Currency;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record Price(@Positive(message = "Price can't be negative") BigDecimal value, Currency currency) {

    public Price addPrice(Price price) {
        return new Price(this.value.add(price.value), Currency.EURO);
    }
}

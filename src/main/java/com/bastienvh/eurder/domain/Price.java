package com.bastienvh.eurder.domain;

import com.bastienvh.eurder.domain.item.Currency;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record Price(@PositiveOrZero BigDecimal value, Currency currency) {

    public Price addPrice(Price price) {
        return new Price(this.value.add(price.value), Currency.EURO);
    }
}

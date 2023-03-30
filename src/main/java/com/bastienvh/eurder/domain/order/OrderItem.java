package com.bastienvh.eurder.domain.order;

import com.bastienvh.eurder.domain.Price;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderItem(int itemId, int amountToBuy, Price price, LocalDate deliveryDate) {
    public Price getSubTotal() {
        return new Price(price.value().multiply(BigDecimal.valueOf(amountToBuy)), price.currency());
    }
}

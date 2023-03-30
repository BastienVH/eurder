package com.bastienvh.eurder.unittests;

import com.bastienvh.eurder.domain.Price;
import com.bastienvh.eurder.domain.item.Currency;
import com.bastienvh.eurder.domain.order.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class OrderItemTest {
    @Test
    void givenOrderItemWithAmount2_whenCalculatingSubTotal_thenCorrectNewPriceIsReturned() {
        //GIVEN
        OrderItem orderItem = new OrderItem(0, 2, new Price(BigDecimal.valueOf(2.5), Currency.EURO), LocalDate.now());
        Price expected = new Price(BigDecimal.valueOf(5.0), Currency.EURO);
        //WHEN
        Price subTotal = orderItem.getSubTotal();
        //THEN
        Assertions.assertThat(subTotal).isEqualTo(expected);
    }
}
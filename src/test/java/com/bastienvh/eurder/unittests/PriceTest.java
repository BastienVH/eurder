package com.bastienvh.eurder.unittests;

import com.bastienvh.eurder.domain.Price;
import com.bastienvh.eurder.domain.item.Currency;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PriceTest {
    @Test
    void given2Prices_whenAddingThem_thenReturnCorrectNewPrice() {
        //GIVEN
        Price price1 = new Price(BigDecimal.valueOf(1.5), Currency.EURO);
        Price price2 = new Price(BigDecimal.valueOf(2.5), Currency.EURO);
        //WHEN
        Price totalPrice = price1.addPrice(price2);
        //THEN
        Assertions.assertThat(totalPrice).isEqualTo(new Price(BigDecimal.valueOf(4.0), Currency.EURO));
    }
}
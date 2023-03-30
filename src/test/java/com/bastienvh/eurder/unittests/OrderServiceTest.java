package com.bastienvh.eurder.unittests;

import com.bastienvh.eurder.domain.order.ItemGroup;
import com.bastienvh.eurder.domain.order.OrderItem;
import com.bastienvh.eurder.domain.Price;
import com.bastienvh.eurder.domain.item.Currency;
import com.bastienvh.eurder.repository.OrderRepository;
import com.bastienvh.eurder.service.ItemService;
import com.bastienvh.eurder.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;
    @Mock
    ItemService itemService;
    @InjectMocks
    OrderService service;

    @Test
    void calculateTotalPrice_givenListOf1OrderItemWithAmount1_thenTotalPriceShouldEqualItemPrice() {
        //GIVEN
        Price givenPrice = new Price(BigDecimal.valueOf(1.99), Currency.EURO);
        OrderItem orderItem = new OrderItem(0, 1, givenPrice, LocalDate.now());
        List<OrderItem> orderItemList = List.of(orderItem);

        //WHEN
        Price result = service.calculateTotalPrice(orderItemList);
        //THEN
        Assertions.assertThat(result).isEqualTo(givenPrice);
    }

    @Test
    void calculateTotalPrice_givenListOf3OrderItemWithAmount2_thenTotalPriceShouldBeCorrect() {
        //GIVEN
        List<OrderItem> orderItemList = List.of(
                new OrderItem(0, 2, new Price(BigDecimal.valueOf(4.5), Currency.EURO), LocalDate.now()),
                new OrderItem(1, 2, new Price(BigDecimal.valueOf(5), Currency.EURO), LocalDate.now()),
                new OrderItem(2, 2, new Price(BigDecimal.valueOf(11), Currency.EURO), LocalDate.now()));
        Price expected = new Price(BigDecimal.valueOf(41.0), Currency.EURO);
        //WHEN
        Price result = service.calculateTotalPrice(orderItemList);
        //THEN
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateDeliveryDate_whenEnoughItemsInStock_thenDeliveryDateIsTomorrow() {
        Mockito.when(itemService.getAmountInStockByItemId(0)).thenReturn(10);
        //GIVEN
        ItemGroup itemGroup = new ItemGroup(0, 5);
        //WHEN
        LocalDate result = service.calculateDeliveryDate(itemGroup);
        //THEN
        Assertions.assertThat(result).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void calculateDeliveryDate_whenNotEnoughItemsInStock_thenDeliveryDateIsIn8Days() {
        Mockito.when(itemService.getAmountInStockByItemId(0)).thenReturn(2);
        //GIVEN
        ItemGroup itemGroup = new ItemGroup(0, 5);
        //WHEN
        LocalDate result = service.calculateDeliveryDate(itemGroup);
        //THEN
        Assertions.assertThat(result).isEqualTo(LocalDate.now().plusDays(8));
    }
}
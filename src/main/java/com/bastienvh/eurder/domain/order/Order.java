package com.bastienvh.eurder.domain.order;

import com.bastienvh.eurder.domain.Price;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final AtomicInteger counter = new AtomicInteger();
    private final int orderId;
    private final UUID customerId;
    private final List<OrderItem> orderItemList;
    private final Price totalPrice;

    public Order(UUID customerId, List<OrderItem> orderItemList,Price totalPrice) {
        orderId = counter.getAndIncrement();
        this.customerId = customerId;
        this.orderItemList = orderItemList;
        this.totalPrice = totalPrice;
    }
    public Integer getId() {
        return orderId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }
}

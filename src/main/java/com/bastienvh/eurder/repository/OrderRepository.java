package com.bastienvh.eurder.repository;

import com.bastienvh.eurder.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    ConcurrentHashMap<Integer, Order> orders;

    public OrderRepository() {
        orders = new ConcurrentHashMap<>();
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public Order getOrderById(int orderId) {
        return orders.get(orderId);
    }
}

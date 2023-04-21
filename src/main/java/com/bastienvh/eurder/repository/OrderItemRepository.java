package com.bastienvh.eurder.repository;

import com.bastienvh.eurder.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    OrderItem findOrderItemByOrderId(int orderId);
}

package com.bastienvh.eurder.domain.order;

import com.bastienvh.eurder.domain.Price;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(sequenceName = "order_id_seq", allocationSize = 1, name = "order_id_seq")
    private int id;
    @Column
    private UUID customerId;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "total_price"))
    private Price totalPrice;

    public Order(UUID customerId, Price totalPrice) {
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Price totalPrice) {
        this.totalPrice = totalPrice;
    }
}

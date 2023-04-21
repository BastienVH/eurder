package com.bastienvh.eurder.domain.order;

import com.bastienvh.eurder.domain.Price;
import com.bastienvh.eurder.domain.item.Item;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_seq")
    @SequenceGenerator(sequenceName = "order_item_id_seq", allocationSize = 1, name = "order_item_id_seq")
    private int id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "order_item")
    private Order order;
    @Column
    private int amountToBuy;
    @Column
    private LocalDate deliveryDate;
    @Embedded
    private Price price;

    public OrderItem(Item item, Order order, int amountToBuy, Price price, LocalDate deliveryDate) {
        this.item = item;
        this.order = order;
        this.amountToBuy = amountToBuy;
        this.price = price;
        this.deliveryDate = deliveryDate;
    }

    public OrderItem() {
    }

    public Price getSubTotal() {
        return new Price(price.getPrice().multiply(BigDecimal.valueOf(amountToBuy)), price.getCurrency());
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getAmountToBuy() {
        return amountToBuy;
    }

    public Price getPrice() {
        return price;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

}

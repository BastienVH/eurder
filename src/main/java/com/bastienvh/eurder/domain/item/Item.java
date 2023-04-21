package com.bastienvh.eurder.domain.item;

import com.bastienvh.eurder.domain.Price;
import jakarta.persistence.*;

import java.util.concurrent.atomic.AtomicInteger;
@Entity
@Table(name = "item")
public class Item {
    private static final AtomicInteger counter = new AtomicInteger();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @SequenceGenerator(sequenceName = "item_id_seq", allocationSize = 1, name = "item_id_seq")
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Embedded
    private Price price;
    @Column
    private int amountOfStock;

    public Item(String name, String description, Price price, int amountOfStock) {
        id = counter.getAndIncrement();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountOfStock = amountOfStock;
    }

    protected Item() {

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public int getAmountOfStock() {
        return amountOfStock;
    }

    public void setAmountOfStock(int amountInStock) {
        this.amountOfStock = amountInStock;
    }
}

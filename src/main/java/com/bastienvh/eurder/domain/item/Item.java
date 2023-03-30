package com.bastienvh.eurder.domain.item;

import com.bastienvh.eurder.domain.Price;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    private static final AtomicInteger counter = new AtomicInteger();
    private final int id;
    private final String name;
    private final String description;
    private Price price;
    private int amountInStock;

    public Item(String name, String description, Price price, int amountInStock) {
        id = counter.getAndIncrement();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
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

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }
}

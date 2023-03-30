package com.bastienvh.eurder.repository;

import com.bastienvh.eurder.domain.item.Item;
import com.bastienvh.eurder.domain.Price;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {
    ConcurrentHashMap<Integer, Item> items;

    public ItemRepository() {
        items = new ConcurrentHashMap<>();
    }

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public Collection<Item> getAllItems() {
        return items.values().stream().toList();
    }

    public int getAmountInStockById(int id) {
        return items.get(id).getAmountInStock();
    }

    public Price getPriceById(int id) {
        return items.get(id).getPrice();
    }

    public void removeAmountOfStockById(int itemId, int amount) {
        Item item = items.get(itemId);
        item.setAmountInStock(item.getAmountInStock() - amount);
    }
}

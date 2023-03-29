package com.bastienvh.eurder.repository;

import com.bastienvh.eurder.domain.item.Item;
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
}

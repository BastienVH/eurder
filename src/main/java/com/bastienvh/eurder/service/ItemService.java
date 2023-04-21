package com.bastienvh.eurder.service;

import com.bastienvh.eurder.domain.Price;
import com.bastienvh.eurder.domain.item.*;
import com.bastienvh.eurder.exceptions.InvalidItemException;
import com.bastienvh.eurder.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemService {
    private final ItemRepository repository;
    private final ItemMapper mapper;

    public ItemService(ItemRepository repository, ItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public int addItem(CreateItemDTO createItemDTO) {
        Item item = mapper.createItemDTOtoItem(createItemDTO);
        repository.save(item);
        return item.getId();
    }

    public Collection<ItemDTO> getAllItems() {
        return repository.findAll()
                .stream()
                .map(mapper::itemToDTO)
                .toList();
    }

    public Item findById(int id) {
        return repository.findById(id).orElseThrow(() -> new InvalidItemException("No item found with id: " + id));
    }

    public int getAmountInStockByItemId(int id) {
        return findById(id).getAmountOfStock();
    }

    public Price getCurrentPriceByItemId(int id) {
        Price currentPrice = findById(id).getPrice();
        return new Price(currentPrice.getPrice(), currentPrice.getCurrency());
    }

    public void removeAmountOfStockById(int itemId, int amount) {
        Item foundItem = findById(itemId);
        foundItem.setAmountOfStock(foundItem.getAmountOfStock() - amount);
        repository.save(foundItem);
    }
}

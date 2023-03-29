package com.bastienvh.eurder.service;

import com.bastienvh.eurder.domain.item.CreateItemDTO;
import com.bastienvh.eurder.domain.item.Item;
import com.bastienvh.eurder.domain.item.ItemDTO;
import com.bastienvh.eurder.domain.item.ItemMapper;
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
        repository.addItem(item);
        return item.getId();
    }

    public Collection<ItemDTO> getAllItems() {
        return repository.getAllItems()
                .stream()
                .map(mapper::itemToDTO)
                .toList();
    }
}

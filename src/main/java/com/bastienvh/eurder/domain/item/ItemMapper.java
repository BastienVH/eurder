package com.bastienvh.eurder.domain.item;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO itemToDTO(Item item) {
        return new ItemDTO(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getAmountInStock());
    }

    public Item createItemDTOtoItem(CreateItemDTO createItemDTO) {
        return new Item(createItemDTO.name(), createItemDTO.description(), createItemDTO.price(), createItemDTO.amountInStock());
    }
}

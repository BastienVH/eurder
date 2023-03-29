package com.bastienvh.eurder.api;

import com.bastienvh.eurder.domain.item.CreateItemDTO;
import com.bastienvh.eurder.domain.item.ItemDTO;
import com.bastienvh.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/items")
public class ItemController {
    ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ItemDTO> getAllItems() {
        return service.getAllItems();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int addItem(@RequestBody CreateItemDTO createItemDTO) {
        return service.addItem(createItemDTO);
    }
}

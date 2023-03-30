package com.bastienvh.eurder.domain.item;

import com.bastienvh.eurder.domain.Price;

public record ItemDTO(int id, String name, String description, Price price, int amountInStock) {
}

package com.bastienvh.eurder.domain.item;

public record ItemDTO(int id, String name, String description, Price price, int amountInStock) {
}

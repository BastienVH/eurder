package com.bastienvh.eurder.domain.item;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

public record ItemDTO(int id, String name, String description, Price price, int amountInStock) {
}

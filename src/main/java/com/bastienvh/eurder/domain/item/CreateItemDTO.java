package com.bastienvh.eurder.domain.item;

import com.bastienvh.eurder.domain.Price;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

public record CreateItemDTO(String name, String description, Price price, int amountInStock) {
    public CreateItemDTO {
        Objects.requireNonNull(name, "no name provided");
        Objects.requireNonNull(description, "no description provided");
        Objects.requireNonNull(price, "no price provided");
        Validate.isTrue(amountInStock >= 0, "Amount in stock can't be negative");
    }
}

package com.bastienvh.eurder.domain.item;

import com.bastienvh.eurder.domain.Price;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateItemDTO(String name, String description, @Valid Price price,
                            @PositiveOrZero(message = "stock can't be negative") int amountInStock) {
}

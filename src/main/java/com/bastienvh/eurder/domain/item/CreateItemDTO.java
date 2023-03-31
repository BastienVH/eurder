package com.bastienvh.eurder.domain.item;

import com.bastienvh.eurder.domain.Price;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateItemDTO(@NotBlank(message = "name can't be empty") String name,
                            @NotBlank(message = "description can't be empty") String description,
                            @Valid Price price,
                            @PositiveOrZero(message = "amount in stock can't be negative") int amountInStock) {
}

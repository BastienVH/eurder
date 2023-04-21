package com.bastienvh.eurder.domain.order;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public record CreateOrderDTO(UUID customerId, @Valid List<ItemGroup> itemGroups) {
}

package com.bastienvh.eurder.domain.order;

import java.util.List;
import java.util.UUID;

public record CreateOrderDTO(UUID customerId, List<ItemGroup> itemGroups) {
}

package com.bastienvh.eurder.domain.order;

import jakarta.validation.constraints.Min;

public record ItemGroup(int itemId, @Min(value = 1, message = "Order amount must be at least 1 per item.") int amountToBuy) {
}

package com.bastienvh.eurder.domain.order;

import com.bastienvh.eurder.domain.Price;

public record OrderConfirmation(int orderId, Price totalPrice) {
}

package com.bastienvh.eurder.api;

import com.bastienvh.eurder.domain.order.CreateOrderDTO;
import com.bastienvh.eurder.domain.order.OrderConfirmation;
import com.bastienvh.eurder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderConfirmation placeOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return service.placeOrder(createOrderDTO);
    }
}

package com.bastienvh.eurder.service;

import com.bastienvh.eurder.domain.*;
import com.bastienvh.eurder.domain.item.Currency;
import com.bastienvh.eurder.domain.order.*;
import com.bastienvh.eurder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    ItemService itemService;
    OrderRepository repository;

    public OrderService(ItemService itemService, OrderRepository repository) {
        this.itemService = itemService;
        this.repository = repository;
    }

    public OrderConfirmation placeOrder(CreateOrderDTO createOrderDTO) {
        //TODO verify customer is valid
        //TODO verify item in itemgroup is in our item repo

        //storing the order in the repo
        UUID customerId = createOrderDTO.customerId();
        List<OrderItem> orderItemList = new ArrayList<>();

        for (ItemGroup itemGroup : createOrderDTO.itemGroups()) {
            LocalDate deliveryDate = calculateDeliveryDate(itemGroup);
            OrderItem orderItem = new OrderItem(itemGroup.itemId(), itemGroup.amountToBuy(), itemService.getCurrentPriceByItemId(itemGroup.itemId()), deliveryDate);
            orderItemList.add(orderItem);
            removeOrderedItemsFromStock(itemGroup.itemId(), itemGroup.amountToBuy());
        }
        Price totalPrice = calculateTotalPrice(orderItemList);
        Order order = new Order(customerId, orderItemList, totalPrice);
        repository.addOrder(order);

        return new OrderConfirmation(order.getId(), totalPrice);
    }

    private void removeOrderedItemsFromStock(int itemId, int amount) {
        itemService.removeAmountOfStockById(itemId, amount);
    }


    public LocalDate calculateDeliveryDate(ItemGroup itemGroup) {
        LocalDate deliveryDate;
        if (itemService.getAmountInStockByItemId(itemGroup.itemId()) > itemGroup.amountToBuy()) {
            deliveryDate = LocalDate.now().plusDays(1);
        } else {
            deliveryDate = LocalDate.now().plusDays(8);
        }
        return deliveryDate;
    }

    public Price calculateTotalPrice(List<OrderItem> orderItemList) {
        Price totalPrice = new Price(BigDecimal.valueOf(0), Currency.EURO);
        for (OrderItem orderItem: orderItemList) {
            totalPrice = totalPrice.addPrice(orderItem.getSubTotal());
        }
        return totalPrice;
    }
}


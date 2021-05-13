package com.task.interview.controllers;

import com.task.interview.dto.CreateOrderRequest;
import com.task.interview.dto.ProductOrder;
import com.task.interview.dto.Order;
import com.task.interview.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    /**
     * Returns orders with products data
     * Imho, would be better to send orders and products separately
     */
    @GetMapping("/orders/{customerId}")
    public List<ProductOrder> getProductsOrders(@PathVariable long customerId) {

        List<com.task.interview.repositories.entities.Order> customerOrders = ordersService.getOrders(customerId);

        return customerOrders.stream().map(o -> ProductOrder.builder()
                .id(o.getId())
                .productId(o.getProduct().getId())
                .price(o.getPrice())
                .productTitle(o.getProduct().getTitle())
                .build()).collect(Collectors.toList());
    }

    @PostMapping("/orders/{customerId}")
    public Order addOrder(@PathVariable long customerId, @RequestBody CreateOrderRequest newOrder) {
        com.task.interview.repositories.entities.Order order = ordersService.createOrder(newOrder.getProductId(), customerId);

        Order result = Order.builder()
                .id(order.getId())
                .productId(order.getProduct().getId())
                .price(order.getPrice())
                .build();

        return result;
    }
}

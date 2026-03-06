package com.flashcart.order.controller;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderEntity createOrder(@RequestParam double amount,
                                   @RequestParam(required = false) String coupon) {

        return orderService.placeOrder(amount, coupon);
    }
}
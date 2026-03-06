package com.flashcart.order.controller;

import com.flashcart.dto.CreateOrderRequest;
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
    public OrderEntity createOrder(@RequestBody CreateOrderRequest request) {

        return orderService.placeOrder(request.getAmount(), request.getCoupon());
    }
}
package com.flashcart.order.controller;

import com.flashcart.dto.CreateOrderRequest;
import com.flashcart.dto.OrderResponse;
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
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request) {

        var order = orderService.placeOrder(request.getAmount(), request.getCoupon());
        return new OrderResponse(order.getId(), order.getAmount(), order.getDiscount(), order.getStatus());
    }
}
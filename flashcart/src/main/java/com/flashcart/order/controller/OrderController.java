package com.flashcart.order.controller;

import com.flashcart.dto.CreateOrderRequest;
import com.flashcart.dto.OrderResponse;
import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.service.CheckoutFacade;
import com.flashcart.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CheckoutFacade checkoutFacade;

    public OrderController(CheckoutFacade checkoutFacade) {
        this.checkoutFacade = checkoutFacade;
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request) {

        var order = checkoutFacade.checkout(request);

        return new OrderResponse(
                order.getId(),
                order.getAmount(),
                order.getDiscount(),
                order.getStatus()
        );
    }
}

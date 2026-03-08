package com.flashcart.shipping.service;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public void ship(OrderEntity order) {

        if (order.getStatus() != OrderStatus.CONFIRMED &&
                order.getStatus() != OrderStatus.READY) {

            throw new RuntimeException("Order cannot be shipped");
        }

        order.setStatus(OrderStatus.SHIPPED);
    }
}
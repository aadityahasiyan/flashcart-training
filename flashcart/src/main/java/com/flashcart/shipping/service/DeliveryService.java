package com.flashcart.shipping.service;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    public void markDelivered(OrderEntity order) {

        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new RuntimeException("Order not shipped yet");
        }

        order.setStatus(OrderStatus.DELIVERED);
    }
}
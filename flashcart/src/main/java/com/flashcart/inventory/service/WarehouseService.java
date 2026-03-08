package com.flashcart.inventory.service;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    public void markReady(OrderEntity order) {

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new RuntimeException("Only confirmed orders can be prepared");
        }

        order.setStatus(OrderStatus.READY);
    }
}
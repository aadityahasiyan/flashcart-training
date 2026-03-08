package com.flashcart.customersupport;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerSupportService {

    public void cancel(OrderEntity order) {

        if (order.getStatus() == OrderStatus.SHIPPED ||
                order.getStatus() == OrderStatus.DELIVERED) {

            throw new RuntimeException("Order cannot be cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);
    }
}
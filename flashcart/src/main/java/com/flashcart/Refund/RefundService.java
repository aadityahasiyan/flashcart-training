package com.flashcart.Refund;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    public void refund(OrderEntity order) {

        if (order.getStatus() != OrderStatus.DELIVERED) {
            throw new RuntimeException("Refund only allowed for delivered orders");
        }

        order.setStatus(OrderStatus.REFUNDED);
    }
}

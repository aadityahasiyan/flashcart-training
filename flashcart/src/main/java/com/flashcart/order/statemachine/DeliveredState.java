package com.flashcart.order.statemachine;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;

public class DeliveredState implements OrderState {

    @Override
    public void refund(OrderEntity order) {
        order.setState(new RefundedState(), OrderStatus.REFUNDED);
    }

    @Override
    public void cancel(OrderEntity order) {
        throw new RuntimeException("Delivered order cannot be cancelled");
    }

    @Override
    public void confirm(OrderEntity order) {
        throw new RuntimeException("Invalid operation");
    }

    @Override
    public void markReady(OrderEntity order) {
        throw new RuntimeException("Invalid operation");
    }

    @Override
    public void ship(OrderEntity order) {
        throw new RuntimeException("Invalid operation");
    }

    @Override
    public void deliver(OrderEntity order) {
        throw new RuntimeException("Already delivered");
    }
}

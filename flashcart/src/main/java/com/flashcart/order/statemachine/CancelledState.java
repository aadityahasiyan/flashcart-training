package com.flashcart.order.statemachine;

import com.flashcart.order.model.OrderEntity;

public class CancelledState implements OrderState {

    @Override
    public void confirm(OrderEntity order) {
        throw new RuntimeException("Cancelled order cannot be confirmed");
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
        throw new RuntimeException("Invalid operation");
    }

    @Override
    public void cancel(OrderEntity order) {
        throw new RuntimeException("Already cancelled");
    }

    @Override
    public void refund(OrderEntity order) {
        throw new RuntimeException("Invalid operation");
    }
}
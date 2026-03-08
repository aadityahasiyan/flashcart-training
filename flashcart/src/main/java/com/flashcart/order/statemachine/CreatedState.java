package com.flashcart.order.statemachine;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;

public class CreatedState implements OrderState {

    @Override
    public void confirm(OrderEntity order) {
        order.setState(new ConfirmedState(), OrderStatus.CONFIRMED);
    }

    @Override
    public void cancel(OrderEntity order) {
        order.setState(new CancelledState(), OrderStatus.CANCELLED);
    }

    @Override
    public void markReady(OrderEntity order) {
        throw new RuntimeException("Order must be confirmed first");
    }

    @Override
    public void ship(OrderEntity order) {
        throw new RuntimeException("Order must be confirmed first");
    }

    @Override
    public void deliver(OrderEntity order) {
        throw new RuntimeException("Order not shipped");
    }

    @Override
    public void refund(OrderEntity order) {
        throw new RuntimeException("Cannot refund");
    }
}
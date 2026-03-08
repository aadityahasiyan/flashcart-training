package com.flashcart.order.statemachine;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;

public class ShippedState implements OrderState {

    @Override
    public void deliver(OrderEntity order) {
        order.setState(new DeliveredState(), OrderStatus.DELIVERED);
    }

    @Override
    public void cancel(OrderEntity order) {
        throw new RuntimeException("Shipped orders cannot be cancelled");
    }

    @Override
    public void confirm(OrderEntity order) {
        throw new RuntimeException("Already processed");
    }

    @Override
    public void markReady(OrderEntity order) {
        throw new RuntimeException("Already shipped");
    }

    @Override
    public void ship(OrderEntity order) {
        throw new RuntimeException("Already shipped");
    }

    @Override
    public void refund(OrderEntity order) {
        throw new RuntimeException("Cannot refund until delivered");
    }
}
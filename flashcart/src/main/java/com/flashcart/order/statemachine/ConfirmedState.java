package com.flashcart.order.statemachine;

import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;

public class ConfirmedState implements OrderState {

    @Override
    public void markReady(OrderEntity order) {
        order.setState(new ReadyState(), OrderStatus.READY);
    }

    @Override
    public void cancel(OrderEntity order) {
        order.setState(new CancelledState(), OrderStatus.CANCELLED);
    }

    @Override
    public void confirm(OrderEntity order) {
        throw new RuntimeException("Already confirmed");
    }

    @Override
    public void ship(OrderEntity order) {
        throw new RuntimeException("Order not ready for shipment");
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
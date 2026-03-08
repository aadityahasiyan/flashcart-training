package com.flashcart.order.statemachine;

import com.flashcart.order.model.OrderEntity;

public interface OrderState {

    void confirm(OrderEntity order);

    void markReady(OrderEntity order);

    void ship(OrderEntity order);

    void deliver(OrderEntity order);

    void cancel(OrderEntity order);

    void refund(OrderEntity order);

}

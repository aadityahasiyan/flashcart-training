package com.flashcart.order.statemachine;

import com.flashcart.order.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderStateFactory {

    public OrderState getState(OrderStatus status) {

        switch (status) {
            case CREATED:
                return new CreatedState();
            case CONFIRMED:
                return new ConfirmedState();
            case READY:
                return new ReadyState();
            case SHIPPED:
                return new ShippedState();
            case DELIVERED:
                return new DeliveredState();
            case CANCELLED:
                return new CancelledState();
            case REFUNDED:
                return new RefundedState();
            default:
                throw new IllegalStateException("Unknown status");
        }
    }
}

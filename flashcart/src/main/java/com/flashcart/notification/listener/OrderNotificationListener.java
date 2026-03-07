package com.flashcart.notification.listener;

import com.flashcart.order.event.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderNotificationListener {

    @EventListener
    public void handle(OrderPlacedEvent event) {
        System.out.println("Sending confirmation email for order " + event.getOrderId());
    }
}

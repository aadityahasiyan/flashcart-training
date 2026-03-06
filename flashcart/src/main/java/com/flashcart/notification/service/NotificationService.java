package com.flashcart.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendOrderConfirmation(Long orderId) {
        System.out.println("Sending confirmation email for order " + orderId);
    }
}
package com.flashcart.payment.service;

import com.flashcart.payment.Gateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGateway gateway;

    public PaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void processPayment(double amount) {
        gateway.charge(amount);
    }
}
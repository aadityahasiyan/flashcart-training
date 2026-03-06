package com.flashcart.payment.service;

import com.flashcart.payment.client.RazorpayClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processPayment(double amount) {

        RazorpayClient client = new RazorpayClient();
        client.charge(amount);
    }
}
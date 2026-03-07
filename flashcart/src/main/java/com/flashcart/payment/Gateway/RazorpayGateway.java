package com.flashcart.payment.Gateway;

import org.springframework.stereotype.Component;

@Component
public class RazorpayGateway implements PaymentGateway {

    public void charge(double amount) {
        System.out.println("Charging Razorpay: " + amount);
    }
}

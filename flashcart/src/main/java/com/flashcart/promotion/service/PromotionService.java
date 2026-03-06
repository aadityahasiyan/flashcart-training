package com.flashcart.promotion.service;

import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    public double calculateDiscount(double amount, String coupon) {

        if (coupon == null) {
            return 0;
        }

        if ("FLAT100".equalsIgnoreCase(coupon)) {
            return 100;
        }

        if ("DIWALI10".equalsIgnoreCase(coupon)) {
            return amount * 0.10;
        }

        return 0;
    }
}
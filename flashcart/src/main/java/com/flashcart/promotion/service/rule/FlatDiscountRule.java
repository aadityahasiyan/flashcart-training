package com.flashcart.promotion.service.rule;

@Component
public class FlatDiscountRule implements PromotionRule {

    public boolean supports(String coupon) {
        return "FLAT100".equalsIgnoreCase(coupon);
    }

    public double calculateDiscount(double amount) {
        return 100;
    }
}
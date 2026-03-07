package com.flashcart.promotion.service.rule;

@Component
public class PercentageDiscountRule implements PromotionRule {

    public boolean supports(String coupon) {
        return "DIWALI10".equalsIgnoreCase(coupon);
    }

    public double calculateDiscount(double amount) {
        return amount * 0.10;
    }
}

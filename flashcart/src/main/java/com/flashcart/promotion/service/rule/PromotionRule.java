package com.flashcart.promotion.service.rule;

public interface PromotionRule {
    public boolean supports(String coupon);
    public double calculateDiscount(double amount);
}

package com.flashcart.promotion.service.rule;

import com.flashcart.price.PricingContext;

public interface PromotionRule {
    boolean supports(PricingContext context, String coupon);
    void apply(PricingContext context, String coupon);

}
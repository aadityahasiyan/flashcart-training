package com.flashcart.promotion.service.rule;

import com.flashcart.price.PricingContext;

public interface PromotionRule {

    void apply(PricingContext context, String coupon);

}
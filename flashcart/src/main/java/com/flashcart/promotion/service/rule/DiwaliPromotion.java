package com.flashcart.promotion.service.rule;

import com.flashcart.price.PricingContext;

public class DiwaliPromotion implements PromotionRule{
    @Override
    public void apply(PricingContext context, String coupon) {

        if ("DIWALI10".equals(coupon)) {
            context.addDiscount(context.getSubtotal() * 0.10);
        }

    }
}

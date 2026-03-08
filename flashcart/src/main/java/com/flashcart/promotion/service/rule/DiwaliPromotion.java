package com.flashcart.promotion.service.rule;

import com.flashcart.price.PricingContext;
import org.springframework.stereotype.Component;

@Component
public class DiwaliPromotion implements PromotionRule{
    @Override
    public boolean supports(PricingContext context, String coupon) {
        return "DIWALI10".equals(coupon);
    }

    @Override
    public void apply(PricingContext context, String coupon) {
        context.addDiscount(context.getSubtotal() * 0.10);
    }
}

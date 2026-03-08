package com.flashcart.promotion.service.rule;

import com.flashcart.price.PricingContext;
import org.springframework.stereotype.Component;

@Component
public class NewUserPromotion implements PromotionRule{
    @Override
    public boolean supports(PricingContext context, String coupon) {
        return false;
    }

    @Override
    public void apply(PricingContext context, String coupon) {

    }
}

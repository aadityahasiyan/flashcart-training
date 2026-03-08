package com.flashcart.promotion.service;

import com.flashcart.cart.CartItem;
import com.flashcart.price.PricingContext;
import com.flashcart.promotion.service.rule.DiwaliPromotion;
import com.flashcart.promotion.service.rule.LoyaltyPromotion;
import com.flashcart.promotion.service.rule.NewUserPromotion;
import com.flashcart.promotion.service.rule.PromotionRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    private final List<PromotionRule> rules;

    public PromotionService() {

        rules = List.of(
                new DiwaliPromotion(),
                new NewUserPromotion(),
                new LoyaltyPromotion()
        );
    }

    public void applyPromotions(PricingContext context, String coupon) {
        for (PromotionRule rule : rules) {
            rule.apply(context, coupon);
        }
    }
}
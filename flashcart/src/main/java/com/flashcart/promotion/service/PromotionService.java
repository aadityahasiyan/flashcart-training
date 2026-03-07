package com.flashcart.promotion.service;

import org.springframework.stereotype.Service;
import com.flashcart.promotion.service.rule.PromotionRule;

import java.util.List;

@Service
public class PromotionService {

    private final List<PromotionRule> rules;

    public PromotionService(List<PromotionRule> rules) {
        this.rules = rules;
    }

    public double calculateDiscount(double amount, String coupon) {

        return rules.stream()
                .filter(rule -> rule.supports(coupon))
                .findFirst()
                .map(rule -> rule.calculateDiscount(amount))
                .orElse(0.0);
    }
}
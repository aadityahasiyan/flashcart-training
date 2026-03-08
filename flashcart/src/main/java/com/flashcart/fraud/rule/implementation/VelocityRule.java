package com.flashcart.fraud.rule.implementation;

import com.flashcart.fraud.model.FraudContext;
import com.flashcart.fraud.rule.abstraction.FraudRule;
import org.springframework.stereotype.Component;

@Component
public class VelocityRule implements FraudRule {

    @Override
    public void evaluate(FraudContext context) {
        // placeholder
    }
}
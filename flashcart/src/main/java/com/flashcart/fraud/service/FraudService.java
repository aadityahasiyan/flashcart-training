package com.flashcart.fraud.service;

import com.flashcart.fraud.model.FraudContext;
import com.flashcart.fraud.rule.abstraction.FraudRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudService {

    private final List<FraudRule> rules;

    public FraudService(List<FraudRule> rules) {
        this.rules = rules;
    }

    public void evaluate(FraudContext context) {

        for (FraudRule rule : rules) {
            rule.evaluate(context);
        }

        if (context.getRiskScore() >= 50) {
            throw new RuntimeException("Fraud detected");
        }
    }
}

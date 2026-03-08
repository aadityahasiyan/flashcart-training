package com.flashcart.fraud.service;

import com.flashcart.fraud.model.FraudContext;
import org.springframework.stereotype.Service;

@Service
public class FraudService {

    public void evaluate(FraudContext context) {

        int risk = 0;

        // high value rule
        if (context.getOrderAmount() > 50000) {
            risk += 30;
        }

        // country mismatch rule (placeholder)
        if (false) {
            risk += 20;
        }

        // velocity rule (placeholder)
        if (false) {
            risk += 15;
        }

        context.addRisk(risk);

        if (context.getRiskScore() >= 50) {
            throw new RuntimeException("Fraud detected");
        }
    }
}

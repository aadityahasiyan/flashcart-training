package com.flashcart.fraud.rule.abstraction;

import com.flashcart.fraud.model.FraudContext;

public interface FraudRule {

    void evaluate(FraudContext context);

}

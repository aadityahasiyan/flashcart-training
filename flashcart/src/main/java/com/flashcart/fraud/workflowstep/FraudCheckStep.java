package com.flashcart.fraud.workflowstep;

import com.flashcart.fraud.model.FraudContext;
import com.flashcart.fraud.service.FraudService;
import com.flashcart.order.model.OrderContext;
import com.flashcart.workflow.OrderStep;

public class FraudCheckStep implements OrderStep {

    private final FraudService fraudService;

    public FraudCheckStep(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @Override
    public void execute(OrderContext context) {

        FraudContext fraudContext =
                new FraudContext(
                        context.getRequest(),
                        context.getPricingContext().getFinalAmount()
                );

        fraudService.evaluate(fraudContext);
    }

    @Override
    public void rollback(OrderContext context) {
        // fraud check does not modify state
    }
}
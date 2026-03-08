package com.flashcart.payment.workflowstep;

import com.flashcart.order.model.OrderContext;
import com.flashcart.workflow.OrderStep;
import com.flashcart.payment.service.PaymentService;

public class ProcessPaymentStep implements OrderStep {

    private final PaymentService paymentService;

    public ProcessPaymentStep(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void execute(OrderContext context) {
        paymentService.processPayment(context.getPricingContext().getFinalAmount());
    }

    @Override
    public void rollback(OrderContext context) {
        // nothing to undo for payment in this example
    }
}
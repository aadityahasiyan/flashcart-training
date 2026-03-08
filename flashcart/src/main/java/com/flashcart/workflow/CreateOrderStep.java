package com.flashcart.workflow;

import com.flashcart.order.model.OrderContext;
import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import com.flashcart.price.PricingContext;

public class CreateOrderStep implements OrderStep {

    @Override
    public void execute(OrderContext context) {

        PricingContext pricing = context.getPricingContext();

        OrderEntity order = new OrderEntity(
                pricing.getSubtotal(),
                pricing.getDiscount(),
                OrderStatus.CREATED
        );

        context.setOrder(order);
    }

    @Override
    public void rollback(OrderContext context) {
        // nothing to undo here
    }
}
package com.flashcart.order.workflow;

import com.flashcart.order.model.OrderContext;

public interface OrderStep {

    void execute(OrderContext context);

    void rollback(OrderContext context);

}

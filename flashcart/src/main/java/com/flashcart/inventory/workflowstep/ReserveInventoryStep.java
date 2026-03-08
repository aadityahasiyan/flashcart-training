package com.flashcart.inventory.workflowstep;

import com.flashcart.cart.CartItem;
import com.flashcart.inventory.service.InventoryService;
import com.flashcart.order.model.OrderContext;
import com.flashcart.order.workflow.OrderStep;

import java.util.List;

public class ReserveInventoryStep implements OrderStep {

    private final InventoryService inventoryService;

    public ReserveInventoryStep(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public void execute(OrderContext context) {
        inventoryService.reserve(context.getRequest().getItems());
    }

    @Override
    public void rollback(OrderContext context) {
        inventoryService.release(context.getRequest().getItems());
    }
}

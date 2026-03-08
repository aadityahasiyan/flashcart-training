package com.flashcart.order.model;

import com.flashcart.dto.CreateOrderRequest;
import com.flashcart.price.PricingContext;

public class OrderContext {

    private final CreateOrderRequest request;
    private final PricingContext pricingContext;
    private OrderEntity order;

    public OrderContext(CreateOrderRequest request, PricingContext pricingContext) {
        this.request = request;
        this.pricingContext = pricingContext;
    }

    public CreateOrderRequest getRequest() {
        return request;
    }

    public PricingContext getPricingContext() {
        return pricingContext;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
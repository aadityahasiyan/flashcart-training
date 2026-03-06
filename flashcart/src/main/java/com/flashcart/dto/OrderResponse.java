package com.flashcart.dto;

import com.flashcart.order.model.OrderStatus;

public class OrderResponse {

    private Long id;
    private double amount;
    private double discount;
    private OrderStatus status;

    public OrderResponse(Long id, double amount, double discount, OrderStatus status) {
        this.id = id;
        this.amount = amount;
        this.discount = discount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscount() {
        return discount;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
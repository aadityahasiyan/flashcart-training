package com.flashcart.fraud.model;

import com.flashcart.dto.CreateOrderRequest;

public class FraudContext {

    private final CreateOrderRequest request;
    private final double orderAmount;
    private int riskScore = 0;

    public FraudContext(CreateOrderRequest request, double orderAmount) {
        this.request = request;
        this.orderAmount = orderAmount;
    }

    public CreateOrderRequest getRequest() {
        return request;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void addRisk(int score) {
        this.riskScore += score;
    }
}

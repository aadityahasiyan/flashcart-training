package com.flashcart.dto;

public class CreateOrderRequest {

    private double amount;
    private String coupon;

    public double getAmount() {
        return amount;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
}
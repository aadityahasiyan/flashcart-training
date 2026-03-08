package com.flashcart.dto;

import com.flashcart.cart.CartItem;

import java.util.List;

public class CreateOrderRequest {

    private List<CartItem> items;
    private String coupon;
    private String paymentMethod;

    public List<CartItem> getItems() {
        return items;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public double getSubtotal() {
        return items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }
}
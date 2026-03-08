package com.flashcart.price;

import com.flashcart.cart.CartItem;

import java.util.List;

public class PricingContext {

    private double subtotal;
    private double discount;
    private double finalAmount;

    private List<CartItem> items;

    public PricingContext(double subtotal, List<CartItem> items) {
        this.subtotal = subtotal;
        this.items = items;
        this.discount = 0;
        this.finalAmount = subtotal;
    }

    public void addDiscount(double value) {
        this.discount += value;
        this.finalAmount = subtotal - discount;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public List<CartItem> getItems() {
        return items;
    }
}

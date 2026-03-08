package com.flashcart.cart;

public class CartItem {

    private String productId;
    private String category;
    private int quantity;
    private double price;

    public CartItem(String productId, String category, int quantity, double price) {
        this.productId = productId;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

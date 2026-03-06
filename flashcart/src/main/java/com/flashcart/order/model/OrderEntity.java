package com.flashcart.order.model;

import jakarta.persistence.*;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private double discount;

    private String status;

    public OrderEntity() {}

    public OrderEntity(double amount, double discount, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
package com.flashcart.order.model;

import com.flashcart.order.statemachine.OrderState;
import com.flashcart.order.statemachine.OrderStateFactory;
import jakarta.persistence.*;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private double discount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Transient
    private OrderState state;

    @Transient
    private OrderStateFactory stateFactory;

    public OrderEntity() {}

    public OrderEntity(double amount, double discount, OrderStatus status) {
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

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public void setState(OrderState state, OrderStatus status) {
        this.state = state;
        this.status = status;
    }

    public OrderState getState() {

        if (state == null) {
            state = stateFactory.getState(this.status);
        }

        return state;
    }

    public void setStateFactory(OrderStateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }
}
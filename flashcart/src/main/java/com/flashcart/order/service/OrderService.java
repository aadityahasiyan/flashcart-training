package com.flashcart.order.service;

import com.flashcart.notification.service.NotificationService;
import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import com.flashcart.order.repository.OrderRepository;
import com.flashcart.payment.service.PaymentService;
import com.flashcart.promotion.service.PromotionService;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PromotionService promotionService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final OrderRepository orderRepository;

    public OrderService(PromotionService promotionService,
                        PaymentService paymentService,
                        NotificationService notificationService,
                        OrderRepository orderRepository) {
        this.promotionService = promotionService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }

    public OrderEntity placeOrder(double amount, String coupon) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (amount > 100000) {
            throw new IllegalArgumentException("Amount exceeds limit");
        }
        // calculate discount
        double discount = promotionService.calculateDiscount(amount, coupon);

        // create order
        OrderEntity order = new OrderEntity(amount, discount, OrderStatus.CREATED);

        // process payment
        paymentService.processPayment(amount - discount);

        // mark order confirmed
        order.setStatus(OrderStatus.CONFIRMED);

        // persist
        orderRepository.save(order);

        // notify customer
        notificationService.sendOrderConfirmation(order.getId());

        return order;
    }
}
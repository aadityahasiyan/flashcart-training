package com.flashcart.order.service;

import com.flashcart.notification.service.NotificationService;
import com.flashcart.order.event.OrderPlacedEvent;
import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import com.flashcart.order.repository.OrderRepository;
import com.flashcart.payment.service.PaymentService;
import com.flashcart.promotion.service.PromotionService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PromotionService promotionService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(PromotionService promotionService,
                        PaymentService paymentService,
                        NotificationService notificationService,
                        OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.promotionService = promotionService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public OrderEntity placeOrder(double amount, String coupon) {

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
        //notificationService.sendOrderConfirmation(order.getId());
        applicationEventPublisher.publishEvent((new OrderPlacedEvent(order.getId())));
        return order;
    }
}
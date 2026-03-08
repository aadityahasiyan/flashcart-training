package com.flashcart.order.service;

import com.flashcart.dto.CreateOrderRequest;
import com.flashcart.notification.service.NotificationService;
import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import com.flashcart.order.repository.OrderRepository;
import com.flashcart.payment.service.PaymentService;
import com.flashcart.price.PricingContext;
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

    public OrderEntity placeOrder(CreateOrderRequest request) {

        // initialize pricing
        PricingContext context =
                new PricingContext(request.getSubtotal(), request.getItems());

        // apply promotions
        promotionService.applyPromotions(context, request.getCoupon());

        // create order
        OrderEntity order = new OrderEntity(
                context.getSubtotal(),
                context.getDiscount(),
                OrderStatus.CREATED
        );

        // process payment
        paymentService.processPayment(context.getFinalAmount());

        // mark order confirmed
        order.setStatus(OrderStatus.CONFIRMED);

        // persist
        orderRepository.save(order);

        // notify customer
        notificationService.sendOrderConfirmation(order.getId());

        return order;
    }
}
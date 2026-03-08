package com.flashcart.order.service;

import com.flashcart.dto.CreateOrderRequest;
import com.flashcart.inventory.service.InventoryService;
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
    private final InventoryService inventoryService;

    public OrderService(
            PromotionService promotionService,
            PaymentService paymentService,
            OrderRepository orderRepository,
            NotificationService notificationService,
            InventoryService inventoryService
    ) {
        this.promotionService = promotionService;
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
        this.inventoryService = inventoryService;
    }

    public OrderEntity placeOrder(CreateOrderRequest request) {

        // initialize pricing
        PricingContext context =
                new PricingContext(request.getSubtotal(), request.getItems());

        // apply promotions
        promotionService.applyPromotions(context, request.getCoupon());

        //reserve inventory
        inventoryService.reserve(request.getItems());

        // create order
        OrderEntity order = new OrderEntity(
                context.getSubtotal(),
                context.getDiscount(),
                OrderStatus.CREATED
        );

        // process payment
        try {
            paymentService.processPayment(context.getFinalAmount());
        } catch (Exception e) {
            inventoryService.release(request.getItems());
            throw e;
        }

        // mark order confirmed
        order.setStatus(OrderStatus.CONFIRMED);

        // persist
        orderRepository.save(order);

        // notify customer
        notificationService.sendOrderConfirmation(order.getId());

        return order;
    }
}
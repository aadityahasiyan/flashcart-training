package com.flashcart.order.service;

import com.flashcart.dto.CreateOrderRequest;
import com.flashcart.fraud.model.FraudContext;
import com.flashcart.fraud.service.FraudService;
import com.flashcart.inventory.service.InventoryService;
import com.flashcart.notification.service.NotificationService;
import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import com.flashcart.order.repository.OrderRepository;
import com.flashcart.order.statemachine.OrderStateFactory;
import com.flashcart.payment.service.PaymentService;
import com.flashcart.price.PricingContext;
import com.flashcart.promotion.service.PromotionService;
import org.springframework.stereotype.Service;

@Service
public class CheckoutFacade {

    private final PromotionService promotionService;
    private final InventoryService inventoryService;
    private final FraudService fraudService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final OrderRepository orderRepository;
    private final OrderStateFactory stateFactory;

    public CheckoutFacade(
            PromotionService promotionService,
            InventoryService inventoryService,
            FraudService fraudService,
            PaymentService paymentService,
            NotificationService notificationService,
            OrderRepository orderRepository,
            OrderStateFactory stateFactory
    ) {
        this.promotionService = promotionService;
        this.inventoryService = inventoryService;
        this.fraudService = fraudService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
        this.stateFactory = stateFactory;
    }

    public OrderEntity checkout(CreateOrderRequest request) {

        // pricing context
        PricingContext pricing =
                new PricingContext(request.getSubtotal(), request.getItems());

        promotionService.applyPromotions(pricing, request.getCoupon());

        // fraud evaluation
        FraudContext fraud =
                new FraudContext(request, pricing.getFinalAmount());

        fraudService.evaluate(fraud);

        // reserve inventory
        inventoryService.reserve(request.getItems());

        // process payment
        paymentService.processPayment(pricing.getFinalAmount());

        // create order
        OrderEntity order = new OrderEntity(
                pricing.getSubtotal(),
                pricing.getDiscount(),
                OrderStatus.CREATED
        );

        // attach state
        order.setStateFactory(stateFactory);

        // lifecycle transition
        order.getState().confirm(order);

        // persist
        orderRepository.save(order);

        // notify
        notificationService.sendOrderConfirmation(order.getId());

        return order;
    }
}

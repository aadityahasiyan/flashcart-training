package com.flashcart.order.service;

import com.flashcart.dto.CreateOrderRequest;
import com.flashcart.fraud.service.FraudService;
import com.flashcart.fraud.workflowstep.FraudCheckStep;
import com.flashcart.inventory.service.InventoryService;
import com.flashcart.inventory.workflowstep.ReserveInventoryStep;
import com.flashcart.notification.service.NotificationService;
import com.flashcart.order.model.OrderContext;
import com.flashcart.order.model.OrderEntity;
import com.flashcart.order.model.OrderStatus;
import com.flashcart.order.repository.OrderRepository;
import com.flashcart.order.statemachine.OrderStateFactory;
import com.flashcart.workflow.OrderStep;
import com.flashcart.payment.service.PaymentService;
import com.flashcart.payment.workflowstep.ProcessPaymentStep;
import com.flashcart.price.PricingContext;
import com.flashcart.promotion.service.PromotionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private final PromotionService promotionService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;
    private final FraudService fraudService;
    private final OrderStateFactory stateFactory;

    public OrderService(
            PromotionService promotionService,
            PaymentService paymentService,
            OrderRepository orderRepository,
            NotificationService notificationService,
            InventoryService inventoryService,
            FraudService fraudService,
            OrderStateFactory orderStateFactory
    ) {
        this.promotionService = promotionService;
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
        this.inventoryService = inventoryService;
        this.fraudService = fraudService;
        this.stateFactory = orderStateFactory;
    }

    public OrderEntity placeOrder(CreateOrderRequest request) {

        // initialize pricing
        PricingContext context =
                new PricingContext(request.getSubtotal(), request.getItems());

        // apply promotions
        promotionService.applyPromotions(context, request.getCoupon());

        // create order
        OrderContext orderContext = new OrderContext(request, context);

        //Steps
        List<OrderStep> steps = List.of(
                new ReserveInventoryStep(inventoryService),
                new FraudCheckStep(fraudService),
                new ProcessPaymentStep(paymentService)
        );

        // process payment
        List<OrderStep> completed = new ArrayList<>();

        try {

            for (OrderStep step : steps) {
                step.execute(orderContext);
                completed.add(step);
            }

        } catch (Exception e) {

            Collections.reverse(completed);

            for (OrderStep step : completed) {
                step.rollback(orderContext);
            }

            throw e;
        }

        // mark order confirmed
        OrderEntity order = new OrderEntity(
                context.getSubtotal(),
                context.getDiscount(),
                OrderStatus.CREATED
        );

        order.setStateFactory(stateFactory);

        //CONFIRM
        order.getState().confirm(order);

        // persist
        orderRepository.save(order);

        // notify customer
        notificationService.sendOrderConfirmation(order.getId());

        return order;
    }

    public void cancelOrder(OrderEntity order) {

        order.getState().cancel(order);
    }

    public void shipOrder(OrderEntity order) {

        order.getState().ship(order);
    }

    public void refundOrder(OrderEntity order) {

        order.getState().refund(order);
    }
}
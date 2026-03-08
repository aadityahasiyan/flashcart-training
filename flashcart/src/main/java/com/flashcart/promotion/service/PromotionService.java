package com.flashcart.promotion.service;

import com.flashcart.cart.CartItem;
import com.flashcart.price.PricingContext;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    public void applyPromotions(PricingContext context, String coupon) {
        applyAutomaticPromotions(context);

        if (coupon != null) {
            applyCouponPromotions(context, coupon);
        }
    }

    private void applyCouponPromotions(PricingContext context, String coupon) {
        if (coupon == null) return;

        double subtotal = context.getSubtotal();

        if (coupon.equals("DIWALI10")) {
            context.addDiscount(subtotal * 0.10);
        }

        if (coupon.equals("HOLI10")) {
            context.addDiscount(subtotal * 0.10);
        }

        if (coupon.equals("NEWYEAR15")) {
            context.addDiscount(subtotal * 0.15);
        }

        if (coupon.equals("INDEPENDENCE20")) {
            context.addDiscount(subtotal * 0.20);
        }

        if (coupon.equals("ANNIVERSARY15")) {
            context.addDiscount(subtotal * 0.15);
        }

        if (coupon.equals("BLACKFRIDAY25")) {
            context.addDiscount(subtotal * 0.25);
        }

        if (coupon.equals("ICICI10")) {
            context.addDiscount(subtotal * 0.10);
        }

        if (coupon.equals("HDFC10")) {
            context.addDiscount(subtotal * 0.10);
        }

        if (coupon.equals("AXIS10")) {
            context.addDiscount(subtotal * 0.10);
        }

        if (coupon.equals("AMEX15")) {
            context.addDiscount(subtotal * 0.15);
        }

        if (coupon.equals("SBI5")) {
            context.addDiscount(subtotal * 0.05);
        }

        if (coupon.equals("NEWUSER15")) {
            context.addDiscount(subtotal * 0.15);
        }

        if (coupon.equals("LOYALTY5")) {
            context.addDiscount(subtotal * 0.05);
        }

        if (coupon.equals("PRIMEUSER10")) {
            context.addDiscount(subtotal * 0.10);
        }

        if (coupon.equals("ABOVE5000") && subtotal > 5000) {
            context.addDiscount(500);
        }

        if (coupon.equals("ABOVE10000") && subtotal > 10000) {
            context.addDiscount(1200);
        }

        if (coupon.equals("BUY2GET1")) {
            for (CartItem item : context.getItems()) {
                if (item.getQuantity() >= 3) {
                    context.addDiscount(item.getPrice());
                }
            }
        }

        if (coupon.equals("BUY3GET1")) {
            for (CartItem item : context.getItems()) {
                if (item.getQuantity() >= 4) {
                    context.addDiscount(item.getPrice());
                }
            }
        }

        if (coupon.equals("IPHONE10")) {
            for (CartItem item : context.getItems()) {
                if (item.getProductId().equals("IPHONE")) {
                    context.addDiscount(item.getPrice() * 0.10);
                }
            }
        }
    }

    private void applyAutomaticPromotions(PricingContext context) {

        double subtotal = context.getSubtotal();

        // Anniversary Sale
        context.addDiscount(subtotal * 0.05);

        // Electronics Festival
        for (CartItem item : context.getItems()) {
            if (item.getCategory().equals("ELECTRONICS")) {
                context.addDiscount(item.getPrice() * 0.10);
            }
        }

        // Fashion Week
        for (CartItem item : context.getItems()) {
            if (item.getCategory().equals("FASHION")) {
                context.addDiscount(item.getPrice() * 0.15);
            }
        }

        // Shoes Weekend
        for (CartItem item : context.getItems()) {
            if (item.getCategory().equals("SHOES")) {
                context.addDiscount(item.getPrice() * 0.08);
            }
        }

        // Big Cart Discount
        if (subtotal > 15000) {
            context.addDiscount(1500);
        }
    }
}
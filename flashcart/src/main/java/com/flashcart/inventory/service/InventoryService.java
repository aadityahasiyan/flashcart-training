package com.flashcart.inventory.service;

import com.flashcart.cart.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    public void reserve(List<CartItem> items) {
        System.out.println("Reserving inventory");
    }

    public void release(List<CartItem> items) {
        System.out.println("Releasing inventory");
    }
}

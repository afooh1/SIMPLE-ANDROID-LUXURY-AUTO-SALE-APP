package com.example.babatundeautos.helpers;

import com.example.babatundeautos.models.Car;
import com.example.babatundeautos.models.CartItem;


import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static final List<CartItem> cartItems = new ArrayList<>();

    public static void addToCart(Car car, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getCar().getName().equals(car.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(car, quantity));
    }

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void clearCart() {
        cartItems.clear();
    }

    public static void removeItem(int index) {
        cartItems.remove(index);
    }

    public static void updateQuantity(int index, int newQuantity) {
        cartItems.get(index).setQuantity(newQuantity);
    }

    public static double getTotalAmount() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuantity() * item.getCar().getPrice();
        }
        return total;
    }
}

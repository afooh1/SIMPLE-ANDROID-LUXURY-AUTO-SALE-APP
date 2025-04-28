package com.example.babatundeautos.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Car car;
    private int quantity;

    public CartItem(Car car, int quantity) {
        this.car = car;
        this.quantity = quantity;
    }

    public Car getCar() {
        return car;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return quantity * car.getPrice();
    }
}

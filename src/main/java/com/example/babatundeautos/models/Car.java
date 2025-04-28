package com.example.babatundeautos.models;
import java.io.Serializable;

public class Car implements Serializable  {
    private String name;
    private String description;
    private double price;
    private String imageUrl;

    public Car() {} // Required for Firebase

    public Car(String name, String description, double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
}

package com.pluralsight.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {
    private List<Product> products;
    private double totalPrice;
    private int totalItems;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isCheckedOut;
    private Map<Product, Integer> productQuantities;
    private String status;
    private double discount;

    // Constructors, getters, and setters

    public Cart(String userId) {
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isCheckedOut = false;
        this.status = "active";
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
        this.totalItems = 0;
        this.discount = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        products.add(product);
        productQuantities.put(product, productQuantities.getOrDefault(product, 0) + quantity);
        totalPrice += product.getPrice() * quantity;
        totalItems += quantity;
        updatedAt = LocalDateTime.now();
    }

    // Other methods for removing products, calculating discounts, etc.

}

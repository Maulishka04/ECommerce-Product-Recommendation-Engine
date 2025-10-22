package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User profile with purchase history and ratings.
 */
public class User {
    private final String id;
    private String name;
    private final PurchaseHistory purchaseHistory = new PurchaseHistory();
    private final List<Rating> ratings = new ArrayList<>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public PurchaseHistory getPurchaseHistory() { return purchaseHistory; }
    public List<Rating> getRatings() { return ratings; }

    public void addRating(Rating r) { ratings.add(r); }
    public void addPurchase(Product p) { purchaseHistory.add(p); }

    @Override
    public String toString() { return String.format("User[%s] %s", id, name); }
}

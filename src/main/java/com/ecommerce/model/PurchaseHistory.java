package com.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PurchaseHistory holds a user's purchased products (simple list wrapper).
 */
public class PurchaseHistory {
    private final List<Product> purchases = new ArrayList<>();

    public void add(Product p) { purchases.add(p); }
    public List<Product> getAll() { return Collections.unmodifiableList(purchases); }
    public boolean isEmpty() { return purchases.isEmpty(); }
}

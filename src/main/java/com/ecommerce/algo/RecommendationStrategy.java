package com.ecommerce.algo;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import java.util.List;

/**
 * Strategy interface for recommendation algorithms.
 */
public interface RecommendationStrategy {
    /**
     * Recommend products for a user from available products.
     * @param user target user
     * @param allProducts pool of products
     * @param allUsers all users for collaborative signals
     * @return ranked list of products
     */
    List<Product> recommend(User user, List<Product> allProducts, List<User> allUsers);
}

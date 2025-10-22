package com.ecommerce.model;

/**
 * Rating given by a user to a product.
 */
public class Rating {
    private final String userId;
    private final String productId;
    private final int stars; // 1-5

    public Rating(String userId, String productId, int stars) {
        this.userId = userId;
        this.productId = productId;
        this.stars = stars;
    }

    public String getUserId() { return userId; }
    public String getProductId() { return productId; }
    public int getStars() { return stars; }
}

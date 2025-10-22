package com.ecommerce.service;

import com.ecommerce.algo.RecommendationStrategy;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import java.util.List;
import java.util.concurrent.*;

/**
 * Service to coordinate recommendation strategies, supports concurrent execution.
 */
public class RecommendationService {
    private final ExecutorService exec = Executors.newFixedThreadPool(3);

    public List<Product> runStrategy(RecommendationStrategy strat, User user, List<Product> allProducts, List<User> allUsers) {
        Future<List<Product>> f = exec.submit(() -> strat.recommend(user, allProducts, allUsers));
        try { return f.get(5, TimeUnit.SECONDS); }
        catch (Exception e){ f.cancel(true); throw new RuntimeException("Recommendation failed", e); }
    }

    public void shutdown(){ exec.shutdown(); }
}

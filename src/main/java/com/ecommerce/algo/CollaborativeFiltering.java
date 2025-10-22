package com.ecommerce.algo;

import com.ecommerce.data.MaxPriorityQueue;
import com.ecommerce.data.SimpleGraph;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import java.util.*;

/**
 * Collaborative Filtering: find similar users by purchase overlap and recommend their products.
 */
public class CollaborativeFiltering implements RecommendationStrategy {
    private final SimpleGraph socialGraph; // optional social influence

    public CollaborativeFiltering(SimpleGraph socialGraph) {
        this.socialGraph = socialGraph;
    }

    @Override
    public List<Product> recommend(User user, List<Product> allProducts, List<User> allUsers) {
        Set<String> target = new HashSet<>();
        for (Product p : user.getPurchaseHistory().getAll()) target.add(p.getId());

        MaxPriorityQueue<Product> pq = new MaxPriorityQueue<>();

        for (User other : allUsers) {
            if (other.getId().equals(user.getId())) continue;
            Set<String> s = new HashSet<>();
            for (Product p : other.getPurchaseHistory().getAll()) s.add(p.getId());
            // Jaccard similarity
            Set<String> inter = new HashSet<>(s); inter.retainAll(target);
            Set<String> uni = new HashSet<>(s); uni.addAll(target);
            double sim = uni.isEmpty() ? 0.0 : ((double) inter.size()) / uni.size();

            // Boost similarity if socially connected
            if (socialGraph != null) {
                List<String> bfs = socialGraph.bfs(user.getId());
                if (bfs.contains(other.getId())) sim *= 1.2; // simple boost
            }

            for (Product p : other.getPurchaseHistory().getAll()) {
                if (target.contains(p.getId())) continue;
                pq.add(p, sim);
            }
        }

        return pq.topN(10);
    }
}

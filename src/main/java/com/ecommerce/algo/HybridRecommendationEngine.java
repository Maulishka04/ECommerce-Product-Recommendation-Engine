package com.ecommerce.algo;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import java.util.*;

/**
 * Simple hybrid engine that merges collaborative and content-based scores.
 */
public class HybridRecommendationEngine implements RecommendationStrategy {
    private final RecommendationStrategy a, b;

    public HybridRecommendationEngine(RecommendationStrategy a, RecommendationStrategy b){ this.a=a; this.b=b; }

    @Override
    public List<Product> recommend(User user, List<Product> allProducts, List<User> allUsers) {
        List<Product> ra = a.recommend(user, allProducts, allUsers);
        List<Product> rb = b.recommend(user, allProducts, allUsers);
        Map<String, Double> score = new HashMap<>();
        int pos=ra.size();
        for (int i=0;i<ra.size();i++) score.put(ra.get(i).getId(), score.getOrDefault(ra.get(i).getId(),0.0)+ (pos - i));
        pos=rb.size();
        for (int i=0;i<rb.size();i++) score.put(rb.get(i).getId(), score.getOrDefault(rb.get(i).getId(),0.0)+ (pos - i));

        List<Map.Entry<String, Double>> entries = new ArrayList<>(score.entrySet());
        entries.sort((x,y)->Double.compare(y.getValue(), x.getValue()));
        List<Product> out = new ArrayList<>();
        for (Map.Entry<String, Double> e : entries) {
            allProducts.stream().filter(p->p.getId().equals(e.getKey())).findFirst().ifPresent(out::add);
            if (out.size()>=10) break;
        }
        return out;
    }
}

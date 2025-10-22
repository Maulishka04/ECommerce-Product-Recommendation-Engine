package com.ecommerce.algo;

import com.ecommerce.data.MaxPriorityQueue;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.utils.SortUtils;

import java.util.*;

/**
 * Content-based filtering based on product attributes.
 */
public class ContentBasedFiltering implements RecommendationStrategy {

    @Override
    public List<Product> recommend(User user, List<Product> allProducts, List<User> allUsers) {
        // build profile vector from user's purchases (category frequency, avg price)
        Map<String, Integer> catFreq = new HashMap<>();
        double avgPrice = 0; int count = 0;
        for (Product p : user.getPurchaseHistory().getAll()){
            catFreq.put(p.getCategory(), catFreq.getOrDefault(p.getCategory(),0)+1);
            avgPrice += p.getPrice(); count++;
        }
        avgPrice = count==0?0:avgPrice/count;

        List<Scored> scores = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (Product p : user.getPurchaseHistory().getAll()) seen.add(p.getId());

        for (Product p : allProducts) {
            if (seen.contains(p.getId())) continue;
            double score = 0;
            // category match
            score += catFreq.getOrDefault(p.getCategory(),0) * 1.5;
            // price proximity
            score += 1.0 / (1.0 + Math.abs(p.getPrice()-avgPrice));
            scores.add(new Scored(p, score));
        }

        // sort descending by score - use merge sort
        SortUtils.mergeSort(scores, Comparator.comparingDouble((Scored s)->s.score).reversed());
        List<Product> out = new ArrayList<>();
        for (int i=0;i<Math.min(10, scores.size());i++) out.add(scores.get(i).product);
        return out;
    }

    private static class Scored { Product product; double score; Scored(Product p,double s){product=p;score=s;} }
}

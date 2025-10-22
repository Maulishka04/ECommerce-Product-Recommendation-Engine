package com.ecommerce.utils;

import com.ecommerce.model.User;

import java.util.*;

/**
 * Basic K-means-like clustering based on user purchase counts. This is a stub for demo.
 */
public class KMeansCluster {
    public static Map<Integer, List<User>> cluster(List<User> users, int k){
        Map<Integer, List<User>> out = new HashMap<>();
        for (int i=0;i<k;i++) out.put(i, new ArrayList<>());
        for (int i=0;i<users.size();i++) out.get(i%k).add(users.get(i));
        return out;
    }
}

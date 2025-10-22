package com.ecommerce.data;

import java.util.*;

/**
 * Simple undirected graph to model user friendships.
 */
public class SimpleGraph {
    private final Map<String, Set<String>> adj = new HashMap<>();

    public void addEdge(String a, String b) {
        adj.computeIfAbsent(a, k -> new HashSet<>()).add(b);
        adj.computeIfAbsent(b, k -> new HashSet<>()).add(a);
    }

    public List<String> bfs(String start) {
        List<String> order = new ArrayList<>();
        if (!adj.containsKey(start)) return order;
        Queue<String> q = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        q.add(start); seen.add(start);
        while (!q.isEmpty()) {
            String cur = q.poll();
            order.add(cur);
            for (String nb : adj.getOrDefault(cur, Collections.emptySet()))
                if (!seen.contains(nb)) { seen.add(nb); q.add(nb); }
        }
        return order;
    }

    public List<String> dfs(String start) {
        List<String> order = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        dfsHelper(start, seen, order);
        return order;
    }

    private void dfsHelper(String cur, Set<String> seen, List<String> out) {
        if (cur == null || seen.contains(cur)) return;
        seen.add(cur); out.add(cur);
        for (String nb : adj.getOrDefault(cur, Collections.emptySet())) dfsHelper(nb, seen, out);
    }
}

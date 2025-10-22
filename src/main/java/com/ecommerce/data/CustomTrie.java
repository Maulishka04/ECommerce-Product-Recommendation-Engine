package com.ecommerce.data;

import com.ecommerce.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trie for product name prefix search.
 */
public class CustomTrie {
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        List<Product> products = new ArrayList<>();
    }

    private final Node root = new Node();

    public void insert(Product p) {
        String key = p.getName().toLowerCase();
        Node cur = root;
        for (char c : key.toCharArray()) {
            cur = cur.children.computeIfAbsent(c, k -> new Node());
            cur.products.add(p);
        }
    }

    public List<Product> searchByPrefix(String prefix) {
        Node cur = root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) return new ArrayList<>();
        }
        return new ArrayList<>(cur.products);
    }
}

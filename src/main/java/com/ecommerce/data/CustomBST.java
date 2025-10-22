package com.ecommerce.data;

import com.ecommerce.model.Product;

/**
 * Simple Binary Search Tree keyed by product id for demo purposes.
 */
public class CustomBST {
    private static class Node {
        Product product;
        Node left, right;
        Node(Product product) { this.product = product; }
    }

    private Node root;

    public void insert(Product p) {
        root = insert(root, p);
    }

    private Node insert(Node node, Product p) {
        if (node == null) return new Node(p);
        int cmp = p.getId().compareTo(node.product.getId());
        if (cmp < 0) node.left = insert(node.left, p);
        else if (cmp > 0) node.right = insert(node.right, p);
        else node.product = p;
        return node;
    }

    public Product find(String id) {
        Node cur = root;
        while (cur != null) {
            int cmp = id.compareTo(cur.product.getId());
            if (cmp == 0) return cur.product;
            cur = cmp < 0 ? cur.left : cur.right;
        }
        return null;
    }
}

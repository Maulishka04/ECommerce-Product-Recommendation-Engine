package com.ecommerce.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple HashTable wrapper for demos.
 */
public class SimpleHashTable<K,V> {
    private final Map<K,V> map = new HashMap<>();
    public void put(K k, V v){ map.put(k,v); }
    public V get(K k){ return map.get(k); }
    public boolean contains(K k){ return map.containsKey(k); }
}

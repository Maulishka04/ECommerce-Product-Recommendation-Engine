package com.ecommerce.data;

import java.util.*;

/**
 * Max-heap wrapper; higher score = higher priority.
 */
public class MaxPriorityQueue<T> {
    private final PriorityQueue<Entry<T>> pq = new PriorityQueue<>(Comparator.reverseOrder());

    public static class Entry<T> implements Comparable<Entry<T>>{
        public final T item; public final double score;
        public Entry(T item, double score){ this.item=item; this.score=score; }
        public int compareTo(Entry<T> o){ return Double.compare(this.score, o.score); }
    }

    public void add(T item, double score){ pq.add(new Entry<>(item, score)); }
    public List<T> topN(int n){ List<T> out = new ArrayList<>(); while(n-->0 && !pq.isEmpty()) out.add(pq.poll().item); return out; }
}

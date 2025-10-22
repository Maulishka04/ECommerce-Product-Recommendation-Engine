package com.ecommerce.data;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Minimal singly linked list implementation.
 */
public class SimpleLinkedList<T> implements Iterable<T> {
    private static class Node<U> { U val; Node<U> next; Node(U v){val=v;} }
    private Node<T> head;
    private int size = 0;

    public void add(T v) {
        Node<T> n = new Node<>(v);
        n.next = head; head = n; size++;
    }
    public int size() { return size; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private Node<T> cur = head;
            public boolean hasNext(){return cur!=null;}
            public T next(){ if(cur==null) throw new NoSuchElementException(); T v=cur.val; cur=cur.next; return v; }
        };
    }
}

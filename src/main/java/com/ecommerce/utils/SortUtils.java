package com.ecommerce.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Utility sorting algorithms (MergeSort) for ranking.
 */
public class SortUtils {
    public static <T> void mergeSort(List<T> list, Comparator<? super T> cmp) {
        if (list.size() <= 1) return;
        int mid = list.size()/2;
        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, list.size()));
        mergeSort(left, cmp);
        mergeSort(right, cmp);
        merge(list, left, right, cmp);
    }

    private static <T> void merge(List<T> out, List<T> a, List<T> b, Comparator<? super T> cmp){
        out.clear();
        int i=0,j=0;
        while(i<a.size() && j<b.size()){
            if(cmp.compare(a.get(i), b.get(j))<=0) out.add(a.get(i++)); else out.add(b.get(j++));
        }
        while(i<a.size()) out.add(a.get(i++));
        while(j<b.size()) out.add(b.get(j++));
    }
}

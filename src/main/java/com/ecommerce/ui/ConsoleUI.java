package com.ecommerce.ui;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import java.util.List;
import java.util.Scanner;

/**
 * Minimal console UI for demonstration and selection.
 */
public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    public int menu(){
        System.out.println("\n=== E-Commerce Recommendation Demo ===");
        System.out.println("1) List users\n2) Select user by index\n3) Exit");
        System.out.print("Choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printUsers(List<User> users){
        for (int i=0;i<users.size();i++) System.out.println(i+": "+users.get(i));
    }

    public int selectUser(int max){
        System.out.print("Select user index: ");
        int idx = Integer.parseInt(scanner.nextLine());
        if (idx<0 || idx>=max) return -1; return idx;
    }

    public void showRecommendations(String title, List<Product> products){
        System.out.println("\n-- "+title+" --");
        for (Product p : products) System.out.println(p);
    }
}

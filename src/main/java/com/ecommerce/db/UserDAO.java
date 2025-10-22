package com.ecommerce.db;

import com.ecommerce.data.SimpleGraph;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public static List<User> sampleUsers(List<Product> products, SimpleGraph graph){
        List<User> users = new ArrayList<>();
        User u1 = new User("u1","Alice"); u1.addPurchase(products.get(0)); u1.addPurchase(products.get(1));
        User u2 = new User("u2","Bob"); u2.addPurchase(products.get(1)); u2.addPurchase(products.get(4));
        User u3 = new User("u3","Carol"); u3.addPurchase(products.get(2)); u3.addPurchase(products.get(4)); u3.addPurchase(products.get(5));
        User u4 = new User("u4","Dave"); u4.addPurchase(products.get(3)); u4.addPurchase(products.get(8));
        User u5 = new User("u5","Eve"); u5.addPurchase(products.get(6)); u5.addPurchase(products.get(5));

        // social ties
        graph.addEdge(u1.getId(), u2.getId());
        graph.addEdge(u2.getId(), u3.getId());
        graph.addEdge(u3.getId(), u5.getId());

        users.add(u1); users.add(u2); users.add(u3); users.add(u4); users.add(u5);
        return users;
    }
}

package com.ecommerce;

import com.ecommerce.algo.CollaborativeFiltering;
import com.ecommerce.algo.ContentBasedFiltering;
import com.ecommerce.algo.HybridRecommendationEngine;
import com.ecommerce.data.SimpleGraph;
import com.ecommerce.db.ProductDAO;
import com.ecommerce.db.UserDAO;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.service.RecommendationService;
import com.ecommerce.ui.ConsoleUI;

import java.util.List;

/**
 * Main runner demonstrating recommendations and A/B comparison.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting E-Commerce Recommendation Engine demo...");

        // load sample data
        List<Product> products = ProductDAO.sampleProducts();
        SimpleGraph graph = new SimpleGraph();
        List<User> users = UserDAO.sampleUsers(products, graph);

        ConsoleUI ui = new ConsoleUI();
        RecommendationService service = new RecommendationService();

        // build strategies
        CollaborativeFiltering cf = new CollaborativeFiltering(graph);
        ContentBasedFiltering cb = new ContentBasedFiltering();
        HybridRecommendationEngine hybrid = new HybridRecommendationEngine(cf, cb);

        // simple flow: list users and compute recommendations for one
        ui.printUsers(users);
        int sel = ui.selectUser(users.size());
        if (sel < 0) { System.out.println("Invalid selection, exiting."); return; }
        User user = users.get(sel);

        // run concurrently (demo): CF, CB, Hybrid
        List<Product> rcf = service.runStrategy(cf, user, products, users);
        List<Product> rcb = service.runStrategy(cb, user, products, users);
        List<Product> rh = service.runStrategy(hybrid, user, products, users);

        ui.showRecommendations("Collaborative Filtering", rcf);
        ui.showRecommendations("Content-Based Filtering", rcb);
        ui.showRecommendations("Hybrid Recommendation", rh);

        System.out.println("\nA/B test comparison: top-1 equality? " + (rcf.size()>0 && rcb.size()>0 && rcf.get(0).getId().equals(rcb.get(0).getId())));

        service.shutdown();
    }
}

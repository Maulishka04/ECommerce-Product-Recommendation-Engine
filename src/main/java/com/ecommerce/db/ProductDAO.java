package com.ecommerce.db;

import com.ecommerce.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple DAO for products. For demo, also supplies sample data.
 */
public class ProductDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);

    public static List<Product> sampleProducts(){
        List<Product> l = new ArrayList<>();
        l.add(new Product("p1","Red T-Shirt","Apparel",19.99));
        l.add(new Product("p2","Blue Jeans","Apparel",49.99));
        l.add(new Product("p3","Running Shoes","Footwear",79.99));
        l.add(new Product("p4","Coffee Mug","Home",9.99));
        l.add(new Product("p5","Wireless Mouse","Electronics",29.99));
        l.add(new Product("p6","Mechanical Keyboard","Electronics",89.99));
        l.add(new Product("p7","Noise Cancelling Headphones","Electronics",199.99));
        l.add(new Product("p8","Yoga Mat","Fitness",24.99));
        l.add(new Product("p9","Water Bottle","Fitness",12.99));
        l.add(new Product("p10","Dress Shirt","Apparel",39.99));
        return l;
    }

    public static List<Product> loadFromDb(){
        List<Product> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT id,name,category,price FROM product")){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) out.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
        } catch (Exception e){ logger.warn("DB load failed, returning empty list", e); }
        return out;
    }
}

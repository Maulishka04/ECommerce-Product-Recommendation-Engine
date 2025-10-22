package com.ecommerce.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC connection helper. Uses H2 in-memory DB for demo if no external DB provided.
 */
public class DBConnection {
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
    private static final String URL = "jdbc:h2:mem:ecom;DB_CLOSE_DELAY=-1";

    public static Connection getConnection() throws SQLException {
        logger.info("Opening H2 in-memory DB connection");
        return DriverManager.getConnection(URL, "sa", "");
    }
}

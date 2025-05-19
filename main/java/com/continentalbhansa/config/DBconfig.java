package com.continentalbhansa.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconfig {
    private static final String DB_NAME = "continental bhansa";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME; 
    private static final String USERNAME = "root";  // Ensure this is correct
    private static final String PASSWORD = "";      // Ensure this is correct

    public static Connection getDbConnection() {
        Connection conn = null;
        try {
            // Ensure the driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the connection to the database
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while connecting to the database: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}


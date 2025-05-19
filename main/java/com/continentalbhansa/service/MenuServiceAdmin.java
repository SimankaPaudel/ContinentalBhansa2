package com.continentalbhansa.service;

import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuServiceAdmin {

    // Fetch all menu items - with logging for debugging
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBconfig.getDbConnection();
            if (conn == null) {
                System.err.println("ERROR: Database connection is null");
                return menuList;
            }
            
            System.out.println("DEBUG: Database connection established successfully");
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            System.out.println("DEBUG: Query executed: " + sql);
            
            int count = 0;
            while (rs.next()) {
                count++;
                int id = rs.getInt("Menu_Items_ID");
                String name = rs.getString("Dish_Name");
                String desc = rs.getString("Menu_Description");
                double price = rs.getDouble("Price");
                String category = rs.getString("Category");
                String imgPath = rs.getString("image_path");
                
                System.out.println("DEBUG: Found menu item: ID=" + id + ", Name=" + name + ", Category=" + category);
                
                MenuItem item = new MenuItem(id, name, desc, price, category);
                item.setImagePath(imgPath);
                menuList.add(item);
            }
            
            System.out.println("DEBUG: Total menu items found: " + count);
            
        } catch (SQLException e) {
            System.err.println("Error fetching menu items: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources properly
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }

        return menuList;
    }

    // Get a menu item by ID - with logging for debugging
    public MenuItem getMenuItemById(int id) {
        String sql = "SELECT * FROM menu_items WHERE Menu_Items_ID=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBconfig.getDbConnection();
            if (conn == null) {
                System.err.println("ERROR: Database connection is null when fetching menu item by ID: " + id);
                return null;
            }
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            System.out.println("DEBUG: Executing query to find menu item with ID: " + id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                MenuItem item = new MenuItem(
                    rs.getInt("Menu_Items_ID"),
                    rs.getString("Dish_Name"),
                    rs.getString("Menu_Description"),
                    rs.getDouble("Price"),
                    rs.getString("Category")
                );
                item.setImagePath(rs.getString("image_path"));
                
                System.out.println("DEBUG: Found menu item: " + item.getName());
                return item;
            } else {
                System.out.println("DEBUG: No menu item found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving menu item: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources properly
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }

        return null;
    }

    // Add a new menu item - with logging for debugging
    public boolean addMenuItem(MenuItem item) {
        String sql = "INSERT INTO menu_items (Dish_Name, Menu_Description, Price, Category, image_path) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBconfig.getDbConnection();
            if (conn == null) {
                System.err.println("ERROR: Database connection is null when adding menu item");
                return false;
            }
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getCategory());
            stmt.setString(5, item.getImagePath());
            
            System.out.println("DEBUG: Adding menu item: " + item.getName() + ", Category: " + item.getCategory());
            int rowsAffected = stmt.executeUpdate();
            
            System.out.println("DEBUG: Rows affected after insert: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding menu item: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Close resources properly
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
    }

    // Update an existing menu item - with logging for debugging
    public boolean updateMenuItem(MenuItem item) {
        String sql = "UPDATE menu_items SET Dish_Name=?, Menu_Description=?, Price=?, Category=?, image_path=? WHERE Menu_Items_ID=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBconfig.getDbConnection();
            if (conn == null) {
                System.err.println("ERROR: Database connection is null when updating menu item");
                return false;
            }
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getCategory());
            stmt.setString(5, item.getImagePath());
            stmt.setInt(6, item.getId());
            
            System.out.println("DEBUG: Updating menu item ID: " + item.getId() + ", Name: " + item.getName());
            int rowsAffected = stmt.executeUpdate();
            
            System.out.println("DEBUG: Rows affected after update: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating menu item: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Close resources properly
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
    }

    // Delete a menu item by ID - with logging for debugging
    public boolean deleteMenuItem(int id) {
        String sql = "DELETE FROM menu_items WHERE Menu_Items_ID=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBconfig.getDbConnection();
            if (conn == null) {
                System.err.println("ERROR: Database connection is null when deleting menu item");
                return false;
            }
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            System.out.println("DEBUG: Deleting menu item with ID: " + id);
            int rowsAffected = stmt.executeUpdate();
            
            System.out.println("DEBUG: Rows affected after delete: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting menu item: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Close resources properly
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database resources: " + e.getMessage());
            }
        }
    }
}
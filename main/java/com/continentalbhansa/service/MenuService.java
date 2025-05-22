package com.continentalbhansa.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.MenuItem;

public class MenuService {
    // CREATE
    public boolean addMenuItem(MenuItem item) {
        String sql = "INSERT INTO menu_items (Dish_Name, Menu_Description, Price, Category, image_path) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getCategory());
            stmt.setString(5, item.getImagePath());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ (all items)
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";

        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setId(rs.getInt("Menu_Items_ID"));
                item.setName(rs.getString("Dish_Name"));
                item.setDescription(rs.getString("Menu_Description"));
                item.setPrice(rs.getDouble("Price"));
                item.setCategory(rs.getString("Category"));
                item.setImagePath(rs.getString("image_path"));

                items.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
    
    // READ with filters (search and category)
    public List<MenuItem> getFilteredMenuItems(String searchTerm, String categoryFilter) {
        List<MenuItem> items = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM menu_items WHERE 1=1");
        List<Object> params = new ArrayList<>();
       
        // Add search condition if search term is provided
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            sqlBuilder.append(" AND (Dish_Name LIKE ? OR Menu_Description LIKE ?)");
            params.add("%" + searchTerm + "%");
            params.add("%" + searchTerm + "%");
        }
        
        // Add category filter if provided
        if (categoryFilter != null && !categoryFilter.trim().isEmpty()) {
            sqlBuilder.append(" AND Category = ?");
            params.add(categoryFilter);
        }
        
        String sql = sqlBuilder.toString();
        
        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setId(rs.getInt("Menu_Items_ID"));
                item.setName(rs.getString("Dish_Name"));
                item.setDescription(rs.getString("Menu_Description"));
                item.setPrice(rs.getDouble("Price"));
                item.setCategory(rs.getString("Category"));
                item.setImagePath(rs.getString("image_path"));

                items.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(items);

        return items;
    }

    // READ (by id)
    public MenuItem getMenuItemById(int id) {
        String sql = "SELECT * FROM menu_items WHERE Menu_Items_ID = ?";
        MenuItem item = null;

        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                item = new MenuItem();
                item.setId(rs.getInt("Menu_Items_ID"));
                item.setName(rs.getString("Dish_Name"));
                item.setDescription(rs.getString("Menu_Description"));
                item.setPrice(rs.getDouble("Price"));
                item.setCategory(rs.getString("Category"));
                item.setImagePath(rs.getString("image_path"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    // UPDATE
    public boolean updateMenuItem(MenuItem item) {
        String sql = "UPDATE menu_items SET Dish_Name = ?, Menu_Description = ?, Price = ?, Category = ?, image_path = ? WHERE Menu_Items_ID = ?";

        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getCategory());
            stmt.setString(5, item.getImagePath());
            stmt.setInt(6, item.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteMenuItem(int id) {
        String sql = "DELETE FROM menu_items WHERE Menu_Items_ID = ?";

        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}



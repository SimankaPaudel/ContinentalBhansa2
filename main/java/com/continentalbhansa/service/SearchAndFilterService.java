package com.continentalbhansa.service;

import com.continentalbhansa.model.MenuItem;
import com.continentalbhansa.config.DBconfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchAndFilterService {

    public List<MenuItem> searchAndFilterMenuItems(String keyword, String category) {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items WHERE (name LIKE ? OR description LIKE ?)";

        // If a specific category is selected, filter by it
        if (category != null && !category.equalsIgnoreCase("All")) {
            sql += " AND category = ?";
        }

        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");

            if (category != null && !category.equalsIgnoreCase("All")) {
                stmt.setString(3, category);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setCategory(rs.getString("category"));
                item.setImagePath(rs.getString("image_path"));
                items.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}

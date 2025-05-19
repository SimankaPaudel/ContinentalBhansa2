package com.continentalbhansa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.User;

public class ProfileService {

    private Connection dbConn;

    public ProfileService() {
        this.dbConn = DBconfig.getDbConnection();
    }

    // 1. Get user profile by username
    public User getUserProfile(String username) {
        if (dbConn == null || username == null) return null;

        String query = "SELECT * FROM users WHERE User_Name = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("User_Name"));
                user.setEmail(rs.getString("Email"));
                user.setPhoneNumber(rs.getString
                		("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setId(rs.getLong("ID"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 2. Update user profile
    public boolean updateUserProfile(User user) {
        if (dbConn == null || user == null || user.getUserName() == null) return false;

        String updateQuery = "UPDATE users SET First_Name = ?, Last_Name = ?, Email = ?, Phone = ?, Address = ? WHERE User_Name = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(updateQuery)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getAddress());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
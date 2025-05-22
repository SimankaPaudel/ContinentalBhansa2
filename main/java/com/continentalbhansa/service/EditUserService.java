package com.continentalbhansa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.User;

public class EditUserService {

	private Connection conn;
	public EditUserService() {
		this.conn = DBconfig.getDbConnection();
	}
	public User getUserById(Long id) {
		String sql = "SELECT * FROM users WHERE User_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	User user = new User();
                	user.setId(id);
                	user.setUserName(rs.getString("User_Name"));
                	user.setEmail(rs.getString("Email"));
                	user.setAddress(rs.getString("Address"));
                	user.setPhoneNumber(rs.getString("Phone"));
                	user.setPasswordHash(rs.getString("Password"));
                	return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
	}
	 public boolean updateUser(User user) {
	        String sql = "UPDATE users SET User_Name = ?, Email = ?, Address = ?, Phone = ? WHERE User_ID = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, user.getUserName());
	            stmt.setString(2, user.getEmail());
	            stmt.setString(3, user.getAddress());
	            stmt.setString(4, user.getPhoneNumber());
	            stmt.setLong(5, user.getId());

	            int rowsUpdated = stmt.executeUpdate();
	            return rowsUpdated > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
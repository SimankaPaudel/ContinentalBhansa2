package com.continentalbhansa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.User;

public class AddUserService {
	private Connection conn;
	public AddUserService() {
		this.conn = DBconfig.getDbConnection();
	}
	
	public boolean addUser(User user) {
		 String sql = "INSERT INTO users (User_Name,Address,Email,Phone,Password,Role_ID) VALUES (?, ?, ?, ?, ?,?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, user.getUserName());
	            pstmt.setString(2, user.getAddress());
	            pstmt.setString(3, user.getEmail());
	            pstmt.setString(4, user.getPhoneNumber());
	            pstmt.setString(5, user.getPasswordHash()); 
	            pstmt.setInt(6,1); 
	            int rows = pstmt.executeUpdate();
	            return rows > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	}
}
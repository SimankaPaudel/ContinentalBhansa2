package com.continentalbhansa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.continentalbhansa.config.DBconfig;

public class DeleteUserService {
	private Connection conn;
	public DeleteUserService() {
		this.conn = DBconfig.getDbConnection();
	}
	public boolean deleteUser(String username) {
		System.out.print(username);
		String query = "DELETE FROM users WHERE User_Name=?";
		 try (PreparedStatement stmt = conn.prepareStatement(query)) {
		        stmt.setString(1, username);
		        int rows = stmt.executeUpdate();
		        return rows > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
	}
}

package com.continentalbhansa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.User;

public class ManageUserService {
	private Connection conn;

	public ManageUserService() {
		this.conn = DBconfig.getDbConnection();
	}

	public List<User> getAllUsers() {
		String query = "SELECT * FROM users";
		List<User> userList = new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("User_ID"));
				user.setUserName(rs.getString("User_Name"));
				user.setEmail(rs.getString("Email"));
				user.setPasswordHash(rs.getString("Password"));
				user.setPhoneNumber(rs.getString("Phone"));
				user.setAddress(rs.getString("Address"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
}
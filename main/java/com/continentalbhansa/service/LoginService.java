package com.continentalbhansa.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.continentalbhansa.util.PasswordUtil;
import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.User;

public class LoginService {
private Connection dbConn;
private final int USER_ROLE = 1;
private final int ADMIN_ROLE = 2;

/**
* Constructor initializes the database connection.
*/
public LoginService() {
this.dbConn = DBconfig.getDbConnection();

}

public Boolean addCustomer(User user) {
if (dbConn == null) {
System.err.println("Database connection is not available.");
return null;
}
String insertQuery = "INSERT INTO users (User_Name,Password,Email,Phone,Address,Role_ID) "
+ "VALUES (?, ?, ?, ?, ?, ?)";
try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
// Insert student details
insertStmt.setString(1, user.getUserName());
insertStmt.setString(2, user.getPasswordHash());
insertStmt.setString(3, user.getEmail());
insertStmt.setString(4, user.getPhoneNumber());
insertStmt.setString(5, user.getAddress());
insertStmt.setLong(6, USER_ROLE);

System.out.println("Executing insert statement");
int rowsAffected = insertStmt.executeUpdate();
System.out.println("Rows affected: " + rowsAffected);

return rowsAffected > 0;
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
return null;
}

}

public String validateLogin(String username, String password) {
if (dbConn == null || username == null || password == null) {
return "";
}
String query = "SELECT Password,Role_ID FROM users WHERE User_Name = ?";

try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
stmt.setString(1, username);
ResultSet rs = stmt.executeQuery();

if (rs.next()) {
String passwordDB = rs.getString("Password");
int role = rs.getInt("Role_ID");
if (password.equals(passwordDB)) {
                   if (role == USER_ROLE) return "user";
                   if (role == ADMIN_ROLE) return "admin";
               }
}

} catch (SQLException e) {
e.printStackTrace();
}
return "";

}

}

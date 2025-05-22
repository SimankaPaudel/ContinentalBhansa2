package com.continentalbhansa.service;
import com.continentalbhansa.model.Reservations;
import com.continentalbhansa.config.DBconfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ReservationService {
    private static final String DB_NAME = "continental bhansa";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    static {
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
    
    private Connection getConnection() throws SQLException {
        return DBconfig.getDbConnection();
    }

    // Helper: Get User_ID by username
    public int getUserIdByUsername(String username) {
        String sql = "SELECT User_ID FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("User_ID");
            } else {
                throw new RuntimeException("User not found: " + username);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user ID", e);
        }
    }

    // Helper: Get Table_ID by table number
    public int getTableIdByNumber(int tableNumber) {
        String sql = "SELECT Table_ID FROM tables WHERE Table_Number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tableNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Table_ID");
            } else {
                throw new RuntimeException("Table not found: " + tableNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching table ID", e);
        }
    }

    // Get Available Tables - NEW METHOD
    public List<Map<String, Object>> getAvailableTables() {
        List<Map<String, Object>> tables = new ArrayList<>();
        String sql = "SELECT Table_ID, Table_Number, Capacity, Status FROM tables WHERE Status = 'AVAILABLE'";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Map<String, Object> table = new HashMap<>();
                table.put("tableId", rs.getInt("Table_ID"));
                table.put("tableNumber", rs.getInt("Table_Number"));
                table.put("capacity", rs.getInt("Capacity"));
                table.put("status", rs.getString("Status"));
                tables.add(table);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching available tables", e);
        }
        return tables;
    }

    // Alternative method to get all tables (if you need all tables regardless of availability)
    public List<Map<String, Object>> getAllTables() {
        List<Map<String, Object>> tables = new ArrayList<>();
        String sql = "SELECT Table_ID, Table_Number, Capacity, Status FROM tables";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Map<String, Object> table = new HashMap<>();
                table.put("tableId", rs.getInt("Table_ID"));
                table.put("tableNumber", rs.getInt("Table_Number"));
                table.put("capacity", rs.getInt("Capacity"));
                table.put("status", rs.getString("Status"));
                tables.add(table);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all tables", e);
        }
        return tables;
    }

    // CREATE
    public void addReservation(Reservations reservation) {
        String sql = "INSERT INTO reservations " +
                     "(Reservation_Date, Reservation_Time, Number_Of_People, Status, User_ID, Table_ID) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            // Disable auto-commit for transaction control
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(reservation.getReservation_Date().getTime()));
            stmt.setTime(2, reservation.getReservation_Time());
            stmt.setInt(3, reservation.getNumber_Of_People());
            stmt.setString(4, reservation.getStatus());
            stmt.setInt(5, reservation.getUser_ID());
            stmt.setInt(6, reservation.getTable_ID());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                conn.commit(); // Commit the transaction
                System.out.println("Reservation added successfully. Rows affected: " + rowsAffected);
            } else {
                conn.rollback();
                throw new RuntimeException("No rows were inserted");
            }
            
        } catch (SQLException e) {
            System.err.println("SQL Error in addReservation: " + e.getMessage());
            e.printStackTrace();
            
            // Rollback transaction on error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            throw new RuntimeException("Error adding reservation: " + e.getMessage(), e);
        } finally {
            // Close resources
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error closing statement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset auto-commit
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
    
   // READ ALL
    public List<Reservations> getAllReservations() {
        List<Reservations> list = new ArrayList<>();
        String sql = "SELECT r.*, u.username as User FROM reservations r JOIN users u ON r.User_ID = u.User_ID";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Reservations res = new Reservations(
                    rs.getInt("Reservation_ID"),
                    rs.getDate("Reservation_Date"),
                    rs.getTime("Reservation_Time"),
                    rs.getInt("Number_Of_People"),
                    rs.getString("Status"),
                    rs.getInt("User_ID"),
                    rs.getInt("Table_ID"),
                    rs.getString("User")
                );
                list.add(res);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching reservations", e);
        }
        return list;
    }
    
    // READ BY ID
   public Reservations getReservationById(int id) {
        String sql = "SELECT r.*, u.username as User FROM reservations r JOIN users u ON r.User_ID = u.User_ID WHERE r.Reservation_ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservations(
                        rs.getInt("Reservation_ID"),
                        rs.getDate("Reservation_Date"),
                        rs.getTime("Reservation_Time"),
                        rs.getInt("Number_Of_People"),
                        rs.getString("Status"),
                       rs.getInt("User_ID"),
                        rs.getInt("Table_ID"),
                        rs.getString("User")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching reservation by ID", e);
        }
        return null;
    }
    
    // UPDATE
   public void updateReservation(Reservations reservation) {
       String sql = "UPDATE reservations SET Reservation_Date=?, Reservation_Time=?, Number_Of_People=?, Status=?, User_ID=?, Table_ID=? WHERE Reservation_ID=?";
       try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setDate(1, new java.sql.Date(reservation.getReservation_Date().getTime()));
           stmt.setTime(2, reservation.getReservation_Time());
           stmt.setInt(3, reservation.getNumber_Of_People());
           stmt.setString(4, reservation.getStatus());
           stmt.setInt(5, reservation.getUser_ID());
           stmt.setInt(6, reservation.getTable_ID());
           stmt.setInt(7, reservation.getReservation_ID());
           stmt.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException("Error updating reservation", e);
       }
   }
    
    
    // DELETE
    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE Reservation_ID=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting reservation", e);
        }
    }

    // Test database connection and table structure
    public void testDatabaseConnection() {
        System.out.println("=== Testing Database Connection ===");
        
        try (Connection conn = getConnection()) {
            System.out.println("✓ Database connection successful");
            
            // Test if reservations table exists and get its structure
            String sql = "DESCRIBE reservations";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                
                System.out.println("✓ Reservations table structure:");
                while (rs.next()) {
                    System.out.println("  - " + rs.getString("Field") + " (" + rs.getString("Type") + ")");
                }
            }
            
            // Test if we can count existing reservations
            String countSql = "SELECT COUNT(*) as total FROM reservations";
            try (PreparedStatement stmt = conn.prepareStatement(countSql);
                 ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    System.out.println("✓ Current reservations count: " + rs.getInt("total"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }


//Add this method to your ReservationService class

//Method to create a guest user if needed
public int createGuestUser(String name, String email, String phone) {
 String checkSql = "SELECT User_ID FROM users WHERE email = ?";
 String insertSql = "INSERT INTO users (username, email, password, phone, role) VALUES (?, ?, ?, ?, ?)";
 
 try (Connection conn = getConnection()) {
     // Check if user already exists
     try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
         checkStmt.setString(1, email);
         ResultSet rs = checkStmt.executeQuery();
         if (rs.next()) {
             return rs.getInt("User_ID");
         }
     }
     
     // Create new guest user
     try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
         insertStmt.setString(1, name);
         insertStmt.setString(2, email);
         insertStmt.setString(3, "guest123"); // Default password for guests
         insertStmt.setString(4, phone);
         insertStmt.setString(5, "GUEST"); // Role
         
         int rowsAffected = insertStmt.executeUpdate();
         if (rowsAffected > 0) {
             ResultSet generatedKeys = insertStmt.getGeneratedKeys();
             if (generatedKeys.next()) {
                 return generatedKeys.getInt(1);
             }
         }
     }
 } catch (SQLException e) {
     System.err.println("Error creating guest user: " + e.getMessage());
     e.printStackTrace();
 }
 
 return -1; // Return -1 if user creation failed
}

//Method to check if user exists
public boolean userExists(int userId) {
 String sql = "SELECT COUNT(*) FROM users WHERE User_ID = ?";
 try (Connection conn = getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)) {
     stmt.setInt(1, userId);
     ResultSet rs = stmt.executeQuery();
     if (rs.next()) {
         return rs.getInt(1) > 0;
     }
 } catch (SQLException e) {
     System.err.println("Error checking user existence: " + e.getMessage());
     e.printStackTrace();
 }
 return false;
}
}
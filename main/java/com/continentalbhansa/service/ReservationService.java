package com.continentalbhansa.service;

import java.sql.*;
import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.Reservations;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    
    // Status constants
    public static final String STATUS_CONFIRMED = "M";  // M for Made/Confirmed
    public static final String STATUS_CANCELLED = "C";  // C for Cancelled
    public static final String STATUS_PENDING = "P";    // P for Pending
    
    // Table availability constants
    public static final String TABLE_AVAILABLE = "y";
    public static final String TABLE_NOT_AVAILABLE = "n";
    
    // Check if table is available for given date, time and number of guests
    public boolean isTableAvailable(String date, String time, int numberOfPeople) {
        try (Connection conn = DBconfig.getDbConnection()) {
            // Check if any table with sufficient capacity is available
            String sql = "SELECT COUNT(*) FROM tables t " +
                        "WHERE t.Seating_Capacity >= ? " +
                        "AND t.Availability_Status = ? " +
                        "AND t.Table_ID NOT IN (" +
                        "    SELECT r.Table_ID FROM reservations r " +
                        "    WHERE r.Reservation_Date = ? " +
                        "    AND r.Reservation_Time = ? " +
                        "    AND r.Status != ?" +
                        ")";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, numberOfPeople);
                pstmt.setString(2, TABLE_AVAILABLE);
                pstmt.setString(3, date);
                pstmt.setString(4, time);
                pstmt.setString(5, STATUS_CANCELLED);
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Create a new table booking
    public boolean createTableBooking(String name, String email, String phone, int numberOfPeople, 
                                    String date, String time, String specialRequest) {
        // Call existing createReservation method with empty address since it's optional
        return createReservation(name, email, phone, "", numberOfPeople, date, time, specialRequest);
    }

    // Create a new reservation
    public boolean createReservation(String name, String email, String phone, String address, 
                                   int numberOfPeople, String date, String time, String specialRequest) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBconfig.getDbConnection();
            conn.setAutoCommit(false);
            
            // First get or create user
            int userId = createOrGetUser(conn, name, email, phone, address);
            
            // Find available table based on seating capacity and availability
            int tableId = findAvailableTable(conn, numberOfPeople);
            if (tableId == -1) {
                return false; // No table available
            }
            
            // Create reservation with status 'M' (Made/Confirmed)
            String sql = "INSERT INTO reservations (Reservation_Date, Reservation_Time, Number_Of_People, Status, User_ID, Table_ID) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            pstmt.setString(2, time);
            pstmt.setInt(3, numberOfPeople);
            pstmt.setString(4, STATUS_CONFIRMED);
            pstmt.setInt(5, userId);
            pstmt.setInt(6, tableId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                // Update table availability
                updateTableAvailability(conn, tableId, TABLE_NOT_AVAILABLE);
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Find available table based on seating capacity
    private int findAvailableTable(Connection conn, int numberOfPeople) throws SQLException {
        String sql = "SELECT Table_ID FROM tables " +
                    "WHERE Seating_Capacity >= ? " +
                    "AND Availability_Status = ? " +
                    "ORDER BY Seating_Capacity ASC LIMIT 1";
                    
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numberOfPeople);
            pstmt.setString(2, TABLE_AVAILABLE);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Table_ID");
            }
            return -1; // No table available
        }
    }
    
    // Update table availability
    private void updateTableAvailability(Connection conn, int tableId, String availability) throws SQLException {
        String sql = "UPDATE tables SET Availability_Status = ? WHERE Table_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, availability);
            pstmt.setInt(2, tableId);
            pstmt.executeUpdate();
        }
    }
    
    // Create or get user
    private int createOrGetUser(Connection conn, String name, String email, String phone, String address) throws SQLException {
        // First try to find existing user
        String findSql = "SELECT User_ID FROM users WHERE Email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(findSql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("User_ID");
            }
        }
        
        // If not found, create new user with default password pattern and Role_ID 1 (customer)
        String createSql = "INSERT INTO users (User_Name, Password, Email, Phone, Address, Role_ID) " +
                          "VALUES (?, ?, ?, ?, ?, 1)";
        try (PreparedStatement pstmt = conn.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, "iam" + name.toLowerCase().split(" ")[0]); // Password pattern: "iam" + first name
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, address);
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new SQLException("Failed to create user");
        }
    }
    
    // Get reservation details with user info
    public Reservations getReservationById(int reservationId) {
        String sql = "SELECT r.*, u.User_Name, u.Email, u.Phone, t.Table_Number, t.Location_Area " +
                    "FROM reservations r " +
                    "JOIN users u ON r.User_ID = u.User_ID " +
                    "JOIN tables t ON r.Table_ID = t.Table_ID " +
                    "WHERE r.Reservation_ID = ?";
                    
        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, reservationId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Reservations(
                    rs.getInt("Reservation_ID"),
                    rs.getDate("Reservation_Date"),
                    rs.getTime("Reservation_Time"),
                    rs.getInt("Number_Of_People"),
                    rs.getString("Status"),
                    rs.getInt("User_ID"),
                    rs.getInt("Table_ID"),
                    rs.getString("User_Name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Cancel reservation and free up the table
    public boolean cancelReservation(int reservationId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBconfig.getDbConnection();
            conn.setAutoCommit(false);
            
            // First get the table ID
            int tableId = getTableIdFromReservation(conn, reservationId);
            if (tableId == -1) return false;
            
            // Update reservation status
            String sql = "UPDATE reservations SET Status = ? WHERE Reservation_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, STATUS_CANCELLED);
            pstmt.setInt(2, reservationId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                // Make table available again
                updateTableAvailability(conn, tableId, TABLE_AVAILABLE);
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private int getTableIdFromReservation(Connection conn, int reservationId) throws SQLException {
        String sql = "SELECT Table_ID FROM reservations WHERE Reservation_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Table_ID");
            }
            return -1;
        }
    }
} 
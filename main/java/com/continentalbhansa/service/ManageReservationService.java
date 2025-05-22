package com.continentalbhansa.service;
 
import com.continentalbhansa.config.DBconfig;
import com.continentalbhansa.model.Reservations;
import com.continentalbhansa.model.User;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
public class ManageReservationService {
 
    private Connection connection;
 
    public ManageReservationService() {
        this.connection = DBconfig.getDbConnection();
        if (this.connection == null) {
            System.out.println("❌ Database connection failed in ManageReservationsService!");
        } else {
            System.out.println("✅ Database connection established.");
        }
    }
 
    public List<Reservations> getAllReservations() {
        List<Reservations> reservationsList = new ArrayList<>();
 
        String query = "SELECT r.Reservation_ID, r.Reservation_Date, r.Reservation_Time, " +
                "r.Number_Of_People, r.Status, r.User_ID, r.Table_ID, " +
                "u.User_Name, u.Phone " +
                "FROM reservations r " +
                "JOIN users u ON r.User_ID = u.User_ID";
 
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
 
            while (rs.next()) {
                // Create and populate User
                User user = new User();
                user.setUserName(rs.getString("User_Name")); // storing full name in firstName field
                user.setPhoneNumber(rs.getString("Phone"));
 
                // Create and populate Reservation
                Reservations reservation = new Reservations(0, null, null, 0, query, 0, 0, "guest");
                reservation.setReservation_ID(rs.getInt("Reservation_ID"));
                reservation.setReservation_Date(rs.getDate("Reservation_Date"));
                reservation.setReservation_Time(rs.getTime("Reservation_Time"));
                reservation.setNumber_Of_People(rs.getInt("Number_Of_People"));
                reservation.setStatus(rs.getString("Status"));
                reservation.setUser_ID(rs.getInt("User_ID"));
                reservation.setTable_ID(rs.getInt("Table_ID"));
                reservation.setUser(rs.getString("User"));
 
                reservationsList.add(reservation);
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return reservationsList;
    }
}
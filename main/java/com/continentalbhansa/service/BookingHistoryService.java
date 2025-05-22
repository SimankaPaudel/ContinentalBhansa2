package com.continentalbhansa.service;

import com.continentalbhansa.model.Reservations;
import com.continentalbhansa.config.DBconfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingHistoryService {

    public List<Reservations> getReservationsByUserId(int userId) {
        List<Reservations> reservationList = new ArrayList<>();

        try (Connection conn = DBconfig.getDbConnection()) {
            String sql = "SELECT r.*, u.Full_Name FROM reservations r JOIN users u ON r.User_ID = u.User_ID WHERE r.User_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reservations reservation = new Reservations(
                    rs.getInt("Reservation_ID"),
                    rs.getDate("Reservation_Date"),
                    rs.getTime("Reservation_Time"),
                    rs.getInt("Number_Of_People"),
                    rs.getString("Status"),
                    rs.getInt("User_ID"),
                    rs.getInt("Table_ID"),
                    rs.getString("Full_Name") // user full name from joined table
                );

                reservationList.add(reservation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservationList;
    }
}

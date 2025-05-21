package com.continentalbhansa.service;



import com.continentalbhansa.model.Reservations;

import com.continentalbhansa.config.DBconfig;

import java.sql.*;

import java.util.ArrayList;

import java.util.List;



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



    // CREATE

    public void addReservation(Reservations reservation) {

        String sql = "INSERT INTO reservations (Reservation_Date, Reservation_Time, Number_Of_People, Status, User_ID, Table_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(reservation.getReservation_Date().getTime()));

            stmt.setTime(2, reservation.getReservation_Time());

            stmt.setInt(3, reservation.getNumber_Of_People());

            stmt.setString(4, reservation.getStatus());

            stmt.setInt(5, reservation.getUser_ID());

            stmt.setInt(6, reservation.getTable_ID());

            stmt.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error adding reservation", e);

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

}


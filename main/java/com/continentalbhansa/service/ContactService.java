package com.continentalbhansa.service;

import com.continentalbhansa.model.Contact;
import com.continentalbhansa.config.DBconfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactService {

    /**
     * Saves a contact message into the database.
     *
     * @param contact The contact object containing name, email, and message.
     * @return true if the message was saved successfully, false otherwise.
     */
    public boolean saveContact(Contact contact) {
        String sql = "INSERT INTO contact (name, email, message) VALUES (?, ?, ?)";

        try (Connection conn = DBconfig.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getEmail());
            pstmt.setString(3, contact.getMessage());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // For debugging, consider logging in production
            return false;
        }
    }
}


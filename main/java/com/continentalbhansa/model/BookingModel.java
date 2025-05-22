package com.continentalbhansa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Model class representing a booking history entry in Continental Bhansa restaurant.
 * This class is specifically designed for the booking history page.
 */
public class BookingModel {
    private int id;
    private String guestName;
    private String tableName;
    private String status;  // "Confirmed", "Cancelled", etc.
    private LocalDate bookingDate;
    private LocalDateTime bookingTime;
    
    // Default constructor
    public BookingModel() {
    }
    
    // Constructor with minimal fields shown in the booking history page
    public BookingModel(int id, String guestName, String tableName, String status) {
        this.id = id;
        this.guestName = guestName;
        this.tableName = tableName;
        this.status = status;
    }
    
    // Full constructor
    public BookingModel(int id, String guestName, String tableName, String status, 
                          LocalDate bookingDate, LocalDateTime bookingTime) {
        this.id = id;
        this.guestName = guestName;
        this.tableName = tableName;
        this.status = status;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getGuestName() {
        return guestName;
    }
    
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
    
    @Override
    public String toString() {
        return "BookingHistory{" +
                "id=" + id +
                ", guestName='" + guestName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", status='" + status + '\'' +
                ", bookingDate=" + bookingDate +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
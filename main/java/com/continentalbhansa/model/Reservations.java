package com.continentalbhansa.model;

import java.sql.Time;
import java.util.Date;

public class Reservations {
    private int Reservation_ID;
    private Date Reservation_Date;
    private Time Reservation_Time;
    private int Number_Of_People;
    private String Status;
    private int User_ID;
    private int Table_ID;
    private String User;
    

    public Reservations(int reservation_Id, Date reservation_date, Time reservation_time,
                        int number_of_people, String status, int user_Id, int table_Id, String User) {
        this.Reservation_ID = reservation_Id;
        this.Reservation_Date = reservation_date;
        this.Reservation_Time = reservation_time;
        this.Number_Of_People = number_of_people;
        this.Status = status;
        this.User_ID = user_Id;
        this.Table_ID = table_Id;
        this.User=User;
    }

    // Getters
    public int getReservation_ID() {
        return Reservation_ID;
    }

    public Date getReservation_Date() {
        return Reservation_Date;
    }

    public Time getReservation_Time() {
        return Reservation_Time;
    }

    public int getNumber_Of_People() {
        return Number_Of_People;
    }

    public String getStatus() {
        return Status;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public int getTable_ID() {
        return Table_ID;
    }
   
    public String getUser() {
        return User;
    }

    // Setters
    public void setReservation_ID(int reservation_ID) {
        this.Reservation_ID = reservation_ID;
    }

    public void setReservation_Date(Date reservation_Date) {
        this.Reservation_Date = reservation_Date;
    }

    public void setReservation_Time(Time reservation_Time) {
        this.Reservation_Time = reservation_Time;
    }

    public void setNumber_Of_People(int number_Of_People) {
        this.Number_Of_People = number_Of_People;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public void setUser_ID(int user_ID) {
        this.User_ID = user_ID;
    }

    public void setTable_ID(int table_ID) {
        this.Table_ID = table_ID;
    }
    public void setUser(String user) {
        this.User = user;
}
}

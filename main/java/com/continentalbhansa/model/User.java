package com.continentalbhansa.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Long id;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String passwordHash;
   
    public User() {
    }

    public User(Long id,String username,String address,String email,String phoneNumber,String passwordHash) {
    this.id = id;
    this.username = username;
    this.address = address;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.passwordHash = passwordHash;
    }
   
    public User(String username,String address,String email,String phoneNumber,String passwordHash) {
    this.username = username;
    this.address = address;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.passwordHash = passwordHash;
    }
   
    public String getAddress() {
    return address;
    }
    public void setAddress(String address) {
    this.address = address;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }
   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
   
}

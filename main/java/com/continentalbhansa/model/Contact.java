package com.continentalbhansa.model;

/**
 * Model class representing a contact message submission.
 */
public class Contact {
    private int id;
    private String name;
    private String email;
    private String message;
    private String submittedDate;

    // Default constructor
    public Contact() {
    }

    // Parameterized constructor
    public Contact(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    // Full parameterized constructor
    public Contact(int id, String name, String email, String message, String submittedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
        this.submittedDate = submittedDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    @Override
    public String toString() {
        return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message 
               + ", submittedDate=" + submittedDate + "]";
    }
}



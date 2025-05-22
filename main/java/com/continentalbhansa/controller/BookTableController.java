package com.continentalbhansa.controller;

import com.continentalbhansa.model.Reservations;
import com.continentalbhansa.service.ReservationService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/BookTable")
public class BookTableController extends HttpServlet {
    private ReservationService service = new ReservationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Test database connection first
            service.testDatabaseConnection();
            
            // Fetch available tables
            List<Map<String, Object>> tables = service.getAvailableTables();
            request.setAttribute("tables", tables);
            
            System.out.println("Found " + tables.size() + " available tables");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Could not load tables: " + e.getMessage());
        }
        request.getRequestDispatcher("/WEB-INF/pages/BookTable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Debug: Print all parameters
            System.out.println("=== BookTable Parameters ===");
            request.getParameterMap().forEach((key, values) -> {
                System.out.println(key + " = " + (values.length > 0 ? values[0] : "null"));
            });

            // Get form parameters with correct field names from JSP
            String dateStr = request.getParameter("reservationDate");  // Changed from "date"
            String timeStr = request.getParameter("reservationTime");  // Changed from "time"
            String peopleStr = request.getParameter("numberOfPeople"); // Changed from "people"
            String tableIdStr = request.getParameter("tableId");
            
            // Additional fields from form (for validation/logging)
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("contactNumber");
            String specialRequest = request.getParameter("specialRequest");

            System.out.println("Parsed parameters:");
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phone);
            System.out.println("Date: " + dateStr);
            System.out.println("Time: " + timeStr);
            System.out.println("People: " + peopleStr);
            System.out.println("Table ID: " + tableIdStr);

            // Validate required parameters
            if (dateStr == null || dateStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Date is required");
            }
            if (timeStr == null || timeStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Time is required");
            }
            if (peopleStr == null || peopleStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Number of people is required");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name is required");
            }
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email is required");
            }

            // Parse and validate parameters
            int numberOfPeople;
            int tableId = 1; // Default table ID if not specified
            Date reservationDate;
            Time reservationTime;

            try {
                numberOfPeople = Integer.parseInt(peopleStr.trim());
                if (numberOfPeople <= 0) {
                    throw new IllegalArgumentException("Number of people must be greater than 0");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number of people: " + peopleStr);
            }

            // Use provided table ID or default to 1
            if (tableIdStr != null && !tableIdStr.trim().isEmpty()) {
                try {
                    tableId = Integer.parseInt(tableIdStr.trim());
                    if (tableId <= 0) {
                        throw new IllegalArgumentException("Invalid table selection");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid table ID: " + tableIdStr);
                }
            }

            // Parse date
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false); // Strict parsing
                reservationDate = dateFormat.parse(dateStr.trim());
                
                // Check if the date is not in the past (allow today)
                Date today = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date todayOnly = sdf.parse(sdf.format(today));
                
                if (reservationDate.before(todayOnly)) {
                    throw new IllegalArgumentException("Reservation date cannot be in the past");
                }
                
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD format");
            }

            // Parse time
            try {
                // Handle HH:mm format from HTML time input
                if (!timeStr.contains(":")) {
                    throw new IllegalArgumentException("Invalid time format");
                }
                
                String[] timeParts = timeStr.split(":");
                if (timeParts.length < 2) {
                    throw new IllegalArgumentException("Invalid time format");
                }
                
                int hours = Integer.parseInt(timeParts[0]);
                int minutes = Integer.parseInt(timeParts[1]);
                
                if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                    throw new IllegalArgumentException("Invalid time values");
                }
                
                // Add seconds if not present (HTML time input gives HH:mm format)
                String timeWithSeconds = timeStr + ":00";
                reservationTime = Time.valueOf(timeWithSeconds);
                
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid time format. Please use HH:MM format (24-hour): " + e.getMessage());
            }

            // Get user ID from session or create guest user
            HttpSession session = request.getSession(false);
            int userId = 1; // Default for demo
            
            if (session != null && session.getAttribute("userId") != null) {
                userId = (Integer) session.getAttribute("userId");
            } else {
                // Check if default user exists, if not create a guest user
                if (!service.userExists(userId)) {
                    // Try to create a guest user with the form data
                    userId = service.createGuestUser(name, email, phone);
                    if (userId == -1) {
                        throw new RuntimeException("Unable to create user for reservation");
                    }
                }
            }
            
            String status = "PENDING";

            System.out.println("Creating reservation with:");
            System.out.println("Date: " + reservationDate);
            System.out.println("Time: " + reservationTime);
            System.out.println("People: " + numberOfPeople);
            System.out.println("User ID: " + userId);
            System.out.println("Table ID: " + tableId);
            System.out.println("Status: " + status);

            // Create Reservations object
            Reservations reservation = new Reservations(
                0, 
                reservationDate, 
                reservationTime, 
                numberOfPeople, 
                status, 
                userId, 
                tableId, 
                ""
            );

            // Store reservation
            service.addReservation(reservation);

            // Success - fetch tables again and set confirmation message
            List<Map<String, Object>> tables = service.getAvailableTables();
            request.setAttribute("tables", tables);
            request.setAttribute("confirmation", 
                "Your reservation has been submitted successfully! " +
                "Name: " + name + ", Date: " + dateStr + ", Time: " + timeStr + 
                ", People: " + numberOfPeople + ", Table ID: " + tableId);
            
            System.out.println("Reservation created successfully");
            
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
            handleError(request, response, e.getMessage());
            return;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            handleError(request, response, "Error creating reservation: " + e.getMessage());
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/pages/BookTable.jsp").forward(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        try {
            List<Map<String, Object>> tables = service.getAvailableTables();
            request.setAttribute("tables", tables);
        } catch (Exception ex) {
            System.err.println("Error loading tables for error page: " + ex.getMessage());
            request.setAttribute("tablesError", "Could not load tables: " + ex.getMessage());
        }
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/WEB-INF/pages/BookTable.jsp").forward(request, response);
    }
}
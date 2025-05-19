package com.continentalbhansa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.continentalbhansa.config.DBconfig;

/**
 * Servlet implementation class Reservationcontroller
 */
@WebServlet("/Reservationcontroller")
public class Reservationcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reservationcontroller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/BookTable.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// Get form data
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("contactNumber");
			int guests = Integer.parseInt(request.getParameter("guests"));
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			String specialRequest = request.getParameter("specialRequest");
			
			// Validate required fields
			if (name == null || email == null || phone == null || date == null || time == null) {
				sendErrorResponse(response, "All fields are required");
				return;
			}
			
			// Get database connection
			conn = DBconfig.getDbConnection();
			
			// First insert into users table
			String userSql = "INSERT INTO users (Full_Name, Email, Phone) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(userSql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.executeUpdate();
			
			// Then insert into table_bookings
			String bookingSql = "INSERT INTO table_bookings (guest_name, email, phone, number_of_guests, booking_date, booking_time, special_request, status) " +
							  "VALUES (?, ?, ?, ?, ?, ?, ?, 'Pending')";
			pstmt = conn.prepareStatement(bookingSql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.setInt(4, guests);
			pstmt.setString(5, date);
			pstmt.setString(6, time);
			pstmt.setString(7, specialRequest);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				// Success response
				response.setContentType("application/json");
				response.getWriter().write("{\"status\":\"success\",\"message\":\"Your table has been booked successfully!\"}");
			} else {
				sendErrorResponse(response, "Failed to book table");
			}
			
		} catch (SQLException e) {
			sendErrorResponse(response, "Database error: " + e.getMessage());
		} catch (NumberFormatException e) {
			sendErrorResponse(response, "Invalid number of guests");
		} catch (Exception e) {
			sendErrorResponse(response, "An error occurred: " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
		response.setContentType("application/json");
		response.getWriter().write("{\"status\":\"error\",\"message\":\"" + message + "\"}");
	}

}

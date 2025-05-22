package com.continentalbhansa.controller;

import com.continentalbhansa.model.Reservations;
import com.continentalbhansa.service.BookingHistoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class bookinghistorycontroller
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/bookinghistorycontroller" })
public class bookinghistorycontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingHistoryService bookinghistoryService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookinghistorycontroller() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void init() throws ServletException {
        bookinghistoryService = new BookingHistoryService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/bookinghistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

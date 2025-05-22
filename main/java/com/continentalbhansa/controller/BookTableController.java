package com.continentalbhansa.controller;



import com.continentalbhansa.model.Reservations;

import com.continentalbhansa.service.ReservationService;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.*;

import java.io.IOException;

import java.sql.Time;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;



@WebServlet("/BookTable")

public class BookTableController extends HttpServlet {

    private ReservationService service = new ReservationService();



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String action = request.getParameter("action");

            if ("edit".equals(action)) {

                try {

                    int id = Integer.parseInt(request.getParameter("id"));

                    Reservations r = service.getReservationById(id);

                    if (r != null) {

                        request.setAttribute("editReservation", r);

                    }

                } catch (NumberFormatException e) {

                    request.setAttribute("error", "Invalid reservation ID");

                }

            }

            

            List<Reservations> reservations = service.getAllReservations();

            request.setAttribute("reservations", reservations);

            request.getRequestDispatcher("/WEB-INF/pages/BookTable.jsp").forward(request, response);

        } catch (Exception e) {

            request.setAttribute("error", "An error occurred while processing your request: " + e.getMessage());

            request.getRequestDispatcher("/WEB-INF/pages/BookTable.jsp").forward(request, response);

        }

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String action = request.getParameter("action");

            if ("delete".equals(action)) {

                try {

                    int id = Integer.parseInt(request.getParameter("id"));

                    service.deleteReservation(id);

                } catch (NumberFormatException e) {

                    request.setAttribute("error", "Invalid reservation ID");

                }

            } else if ("update".equals(action)) {

                try {

                    Reservations r = getReservationFromRequest(request);

                    service.updateReservation(r);

                } catch (Exception e) {

                    request.setAttribute("error", "Error updating reservation: " + e.getMessage());

                }

            } else {

                try {

                    Reservations r = getReservationFromRequest(request);

                    service.addReservation(r);

                } catch (Exception e) {

                    request.setAttribute("error", "Error creating reservation: " + e.getMessage());

                }

            }

            response.sendRedirect("BookTable");

        } catch (Exception e) {

            request.setAttribute("error", "An error occurred while processing your request: " + e.getMessage());

            request.getRequestDispatcher("/WEB-INF/pages/Book Table.jsp").forward(request, response);

        }

    }



    private Reservations getReservationFromRequest(HttpServletRequest request) {

        try {

            int reservationId = request.getParameter("reservationId") != null ? Integer.parseInt(request.getParameter("reservationId")) : 0;

            Date reservationDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("reservationDate"));

            Time reservationTime = Time.valueOf(request.getParameter("reservationTime"));

            int numberOfPeople = Integer.parseInt(request.getParameter("numberOfPeople"));

            String status = request.getParameter("status");

            int userId = Integer.parseInt(request.getParameter("userId"));

            int tableId = Integer.parseInt(request.getParameter("tableId"));

            String user = ""; // You can fetch username if needed

            return new Reservations(reservationId, reservationDate, reservationTime, numberOfPeople, status, userId, tableId, user);

        } catch (Exception e) {

            throw new RuntimeException("Invalid reservation data: " + e.getMessage(), e);

        }

    }

}
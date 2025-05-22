package com.continentalbhansa.controller;

import com.continentalbhansa.model.User;
import com.continentalbhansa.service.EditUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet implementation class EditUserController
 */
@WebServlet("/EditUserController")
public class EditUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditUserController() {
        super();
    }

    // Load existing user data into the form
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("id"));
        EditUserService editUserService = new EditUserService();
        User user = editUserService.getUserById(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/EditUser.jsp").forward(request, response);
    }

    // Handle form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        User user = new User(id, username, address, email, phone, null); // Keep passwordHash unchanged

        EditUserService editUserService = new EditUserService();
        boolean success = editUserService.updateUser(user);

        if (success) {
            response.sendRedirect("manage_user");
        } else {
            request.setAttribute("errorMessage", "Failed to update user.");
            request.getRequestDispatcher("/WEB-INF/pages/EditUser.jsp").forward(request, response);
        }
    }
}

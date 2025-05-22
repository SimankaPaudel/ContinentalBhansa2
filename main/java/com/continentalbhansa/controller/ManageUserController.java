package com.continentalbhansa.controller;

import java.io.IOException;
import java.util.List;

import com.continentalbhansa.model.User;
import com.continentalbhansa.service.ManageUserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = "/manage_user")
public class ManageUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManageUserService manageUserService =  new ManageUserService();
		List<User> allUsers = manageUserService.getAllUsers();
		 if (allUsers == null || allUsers.isEmpty()) {
		        req.setAttribute("errorMsg", "No users found.");
		    } else {
		        req.setAttribute("userList", allUsers);
		    }
		req.getRequestDispatcher("WEB-INF/pages/manage_user.jsp").forward(req, resp);
	}
}

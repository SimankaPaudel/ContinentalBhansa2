package com.continentalbhansa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.continentalbhansa.model.User;
import com.continentalbhansa.service.LoginService;
import com.continentalbhansa.util.ValidationUtil;
import com.continentalbhansa.util.cookieUtil;

/**
 * Servlet implementation class Logincontroller
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Logincontroller" })
public class Logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LoginService loginService;
	private static String ADMIN_USER = "admin";
	private static String ADMIN_PASSWORD = "admin";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logincontroller() {
		super();
		loginService = new LoginService();
// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			System.out.println("Login attempt: " + userName);

			if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
				request.setAttribute("error", "Username and password are required.");
				request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
				return;
			}


			String isValid = loginService.validateLogin(userName, password);
			if (isValid == "user") {
				request.setAttribute("success", "Login successful!");
				cookieUtil.addCookie(response, "role", "user", 60 * 60); 
				request.getSession().setAttribute("username", userName); 
				request.getRequestDispatcher("WEB-INF/pages/UserDashboard.jsp").forward(request, response);
			} else if (isValid == "admin") {
				request.setAttribute("success", "Login successful!");
				cookieUtil.addCookie(response, "role", "admin", 60 * 60); 
				request.getSession().setAttribute("Admin User", userName); 
				request.getRequestDispatcher("WEB-INF/pages/admin_dashboard.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Invalid username or password.");
				request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "An unexpected error occurred.");
			request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
		}
	}
}

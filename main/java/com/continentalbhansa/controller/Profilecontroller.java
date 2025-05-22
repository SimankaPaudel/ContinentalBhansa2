package com.continentalbhansa.controller;

import com.continentalbhansa.model.User;
import com.continentalbhansa.service.ProfileService;
import com.continentalbhansa.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation for handling user profile display and updates.
 */
@WebServlet(urlPatterns = { "/Profilecontroller" })
public class Profilecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Profilecontroller() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    String error = (String) session.getAttribute("error");
	    if (error != null) {
	        request.setAttribute("error", error);
	        session.removeAttribute("error");  // ✅ Clear it so it disappears next time
	    }

	    if (session != null && session.getAttribute("username") != null) {
	    	String successMessage = (String) session.getAttribute("successMessage");
	        if (successMessage != null) {
	            request.setAttribute("successMessage", successMessage);
	            session.removeAttribute("successMessage");
	        }
	        String username = (String) session.getAttribute("username");

	        ProfileService profileService = new ProfileService();
	        User userProfile = profileService.getUserProfile(username);

	        

	        request.setAttribute("user", userProfile);
	        request.getRequestDispatcher("/WEB-INF/pages/Profile.jsp").forward(request, response);
	    } else {
	        response.sendRedirect("Logincontroller");
	    }
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		String  message = validateCustomerForm(phone);
		if(message !=null) {
	    	session.setAttribute("error",message);
	    	response.sendRedirect("Profilecontroller");
	    	return;
	    }

		else {
		ProfileService profileService = new ProfileService();
		String userName = (String) session.getAttribute("username");
		System.out.print(userName);
		User user = profileService.getUserProfile(userName);
		System.out.print(user);
		if (user != null) {
			user.setUserName(username);
			user.setEmail(email);
			user.setPhoneNumber(phone);
			user.setAddress(address);
		
			boolean success = profileService.updateUserProfile(user,userName);
			System.out.print(success);
			if (success) {
				session.setAttribute("successMessage", "Profile updated successfully.");
				response.sendRedirect("Profilecontroller");
			} else {
				 	request.setAttribute("user", user);  
				    request.setAttribute("errorMessage", "Failed to update profile.");
				    request.getRequestDispatcher("/WEB-INF/pages/Profile.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect("Logincontroller");
		}
		}
	}
	private String validateCustomerForm(String phone) {
       
		if (ValidationUtil.isNullOrEmpty(phone))
			return "Phone Number is required.";
		if (!ValidationUtil.isValidPhoneNumber(phone))
			return "Phone number must be 10 digits and start with 98.";
		return null; // All validations passed
	}

}

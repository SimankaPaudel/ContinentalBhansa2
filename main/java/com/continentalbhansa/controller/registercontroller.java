package com.continentalbhansa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.continentalbhansa.model.User;
import com.continentalbhansa.service.LoginService;
import com.continentalbhansa.util.PasswordUtil;
import com.continentalbhansa.util.ValidationUtil;

/**
 * Servlet implementation class registercontroller
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/registercontroller" })
public class registercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registercontroller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    String username = request.getParameter("username");
	    String address = request.getParameter("address");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phoneNumber");
	    String password = request.getParameter("password");
	    String message=validateCustomerForm(request);
	    String passwordHash = PasswordUtil.encrypt(username, password);
	    if(message !=null) {
	    	handleError(request,response,message);
	    }
	    else {
	    User user = new User();
	    user.setUserName(username);
	    user.setEmail(email);
	    user.setAddress(address);
	    user.setPhoneNumber(phone);
	    user.setPasswordHash(passwordHash);
	    

	    LoginService loginService = new LoginService();
	    
	    boolean isRegistered = loginService.addCustomer(user);
	    if(isRegistered) {
	    	request.setAttribute("success", "Register successful!"); // Optional
			request.getRequestDispatcher("WEB-INF/pages/Login.jsp").forward(request, response);
	    }
	    else {
			request.setAttribute("error", "Error Registering the Customer");
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	    }
	    }
	}
	
	private String validateCustomerForm(HttpServletRequest request) {
		String username = request.getParameter("username");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String reTypePassword = request.getParameter("reTypePassword");
       
        
        System.out.println("Validating Username: [" + username+ "]");
        

		if (ValidationUtil.isNullOrEmpty(username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(address))
			return "Address is required.";
		if (ValidationUtil.isNullOrEmpty(phone))
			return "Phone Number is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(reTypePassword))
			return "Confirm Password is required.";
		
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidPhoneNumber(phone))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(phone))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidationUtil.doPasswordsMatch(password, reTypePassword))
			return "Passwords do not match.";
		return null; // All validations passed
	}
	
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("address", req.getParameter("address"));
		req.setAttribute("phoneNumber", req.getParameter("phoneNumber"));
		req.setAttribute("email", req.getParameter("email"));
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	
	}

}

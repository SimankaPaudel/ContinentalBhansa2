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
//	private static String ADMIN_USER = "admin";
//	private static String ADMIN_PASSWORD = "admin";

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
		System.out.println("Logincontroller doPost() called");
		try {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			System.out.println("Login attempt: " + userName);

			if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
				request.setAttribute("error", "Username and password are required.");
				request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
				return;
			}

// if (ADMIN_USER.equals(userName) && ADMIN_PASSWORD.equals(password)) {
// request.getRequestDispatcher("WEB-INF/pages/admin_dashboard.jsp").forward(request, response);
// return;
// }		
			System.out.println("Login attempt: " + userName);
			System.out.println("Password attempt: " + password);

			String isValid = loginService.validateLogin(userName, password);
			System.out.println("Validation result: " + isValid);
			if (isValid == "user") {
				request.setAttribute("success", "Login successful!");
				cookieUtil.addCookie(response, "role", "user", 60 * 60); // Optional
				request.getSession().setAttribute("username", userName); // Optional
				System.out.println("User role set in session: " + userName);
				request.getRequestDispatcher("WEB-INF/pages/UserDashboard.jsp").forward(request, response);
			} else if (isValid == "admin") {
				request.setAttribute("success", "Login successful!");
				cookieUtil.addCookie(response, "role", "admin", 60 * 60); // Optional
				request.getSession().setAttribute("Admin User", userName); // Optional
				System.out.println("Admin role set in session: " + userName);
				request.getRequestDispatcher("WEB-INF/pages/admin_dashboard.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Invalid username or password.");
				System.out.println("Invalid login attempt: " + userName);
				request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "An unexpected error occurred.");
			System.out.println("Unexpected error in login: " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
		}
	}
// TODO Auto-generated method stub
// try {
// String validationMessage = validateRegistrationForm(request);
// if (validationMessage != null) {
// return;
// }
// else {
// User user = createCustomerFromRequest(request);
// Boolean result = loginService.addCustomer(user);
// if (result != null && result) {
// request.setAttribute("success", "Login successful!");
// request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
// } else {
//// handleError(request, response, "Failed to register. Please try again.");
// }
// }
// }
// catch (Exception e) {
// System.err.println("Unexpected error in registration: " + e.getMessage());
//        e.printStackTrace();
////        handleError(request, response, "An unexpected error occurred: " + e.getMessage());
// }

// TODO Auto-generated method stub
// private String validateRegistrationForm(HttpServletRequest req) {
// String firstName = req.getParameter("firstName");
// String lastName = req.getParameter("lastName");
// String username = req.getParameter("username");
//// String gender = req.getParameter("gender");
// String email = req.getParameter("email");
// String number = req.getParameter("phoneNumber");
// String password = req.getParameter("password");
// String retypePassword = req.getParameter("reTypePassword");
//
// System.out.println("Validating firstName: [" + firstName + "]");
//
// // Check for null or empty fields first
// if (!ValidationUtil.validateName(firstName))
// return "First name is required.";
//// if (ValidationUtil.isNullOrEmpty(lastName))
//// return "Last name is required.";
//// if (ValidationUtil.isNullOrEmpty(username))
//// return "Username is required.";
////// if (ValidationUtil.isNullOrEmpty(dobStr))
////// return "Date of birth is required.";
////// if (ValidationUtil.isNullOrEmpty(gender))
////// return "Gender is required.";
//// if (ValidationUtil.isNullOrEmpty(email))
//// return "Email is required.";
////// if (ValidationUtil.isNullOrEmpty(number))
////// return "Phone number is required.";
//// if (ValidationUtil.isNullOrEmpty(password))
//// return "Password is required.";
//// if (ValidationUtil.isNullOrEmpty(retypePassword))
//// return "Please retype the password.";
//
// // Convert date of birth
//// LocalDate dob;
//// try {
//// dob = LocalDate.parse(dobStr);
//// } catch (Exception e) {
//// return "Invalid date format. Please use YYYY-MM-DD.";
//// }
//
// // Validate fields
//// if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
//// return "Username must start with a letter and contain only letters and numbers.";
////// if (!ValidationUtil.isValidGender(gender))
////// return "Gender must be 'male' or 'female'.";
//// if (!ValidationUtil.isValidEmail(email))
//// return "Invalid email format.";
////// if (!ValidationUtil.isValidPhoneNumber(number))
////// return "Phone number must be 10 digits and start with 98.";
//// if (!ValidationUtil.isValidPassword(password))
//// return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
//// if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
//// return "Passwords do not match.";
////
////
//// if (!ValidationUtil.isAlphabetic(firstName))
//// return "Firstname must contain letters and no numeric digits.";
//// if (!ValidationUtil.isAlphabetic(lastName))
//// return "Lastname must contain letters and no numeric digits.";
//
// // Check if the date of birth is at least 16 years before today
//// if (!ValidationUtil.isAgeAtLeast16(dob))
//// return "You must be at least 16 years old to register.";
////
// return null; // All validations passed
// }
// private User createCustomerFromRequest(HttpServletRequest req) {
//    User customer = new User();
//    customer.setFirstName(req.getParameter("firstName"));
//    customer.setLastName(req.getParameter("lastName"));
//    customer.setEmail(req.getParameter("email"));
//    customer.setPhoneNumber(req.getParameter("phoneNumber"));
//    customer.setAddress(req.getParameter("address"));
//    
//    // Ideally, you should hash the password before storing it
//    customer.setPasswordHash(req.getParameter("password"));
//    
//    // Registration date is set in the constructor
//    return customer;
// }

}
// private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
// throws ServletException, IOException {
// req.setAttribute("error", message);
// req.setAttribute("firstName", req.getParameter("firstName"));
// req.setAttribute("lastName", req.getParameter("lastName"));
// req.setAttribute("username", req.getParameter("username"));
//// req.setAttribute("dob", req.getParameter("dob"));
//// req.setAttribute("gender", req.getParameter("gender"));
// req.setAttribute("email", req.getParameter("email"));
// req.setAttribute("phoneNumber", req.getParameter("phoneNumber"));
// req.setAttribute("subject", req.getParameter("subject"));
// req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);
// }
package com.continentalbhansa.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.continentalbhansa.util.cookieUtil;
import com.continentalbhansa.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthFilter implements Filter{
	private static final String LOGIN = "/Logincontroller";
	private static final String REGISTER = "/registercontroller";
	private static final String HOME = "/UserDashboard";
	private static final String ROOT = "/";
	private static final String DASHBOARD = "/AdminDashboardController";
	private static final String RESERVATION = "/manage_reservation";
	private static final String MENU_ITEMS = "/MenuController";
	private static final String MANAGE_MENU = "/admin/manage_menu";
	private static final String ABOUT = "/aboutuscontroller";
	private static final String PROFILE = "/Profilecontroller";
	private static final String CONTACT = "/contactuscontroller";
	private static final String PAYMENT= "/Paymentcontroller";
	private static final String ADD_USER = "/AddUserController";
	private static final String EDIT_USER = "/EditUserController";
	private static final String DELETE_USER = "/DeleteUserController";
	private static final String BOOKING_HISTORY= "/bookinghistorycontroller";
	private static final String MANAGE_USER = "/ManageUserController";
	private static final String SEARCH_AND_FILTER= "/SearchAndFiltercontroller";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 //Initialization logic, if required
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {

	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse res = (HttpServletResponse) response;

	String uri = req.getRequestURI();
		
		 //Allow access to resources
	if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css")) {
	chain.doFilter(request, response);
	return;
	}
		
	//boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
	String userRole = cookieUtil.getCookie(req, "role") != null ? cookieUtil.getCookie(req, "role").getValue()
	: null;

	if ("admin".equals(userRole)) {
			 //Admin is logged in
	if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
	res.sendRedirect(req.getContextPath() + DASHBOARD);
	} else if (uri.endsWith(DASHBOARD) || uri.endsWith(RESERVATION) || uri.endsWith(MANAGE_MENU)
	|| uri.endsWith(PAYMENT) || uri.endsWith(ADD_USER)|| uri.endsWith(HOME) || uri.endsWith(ROOT))  {
	chain.doFilter(request, response);
	} else if (uri.endsWith(PROFILE) || uri.endsWith(MANAGE_USER)
	|| uri.endsWith(BOOKING_HISTORY) || uri.endsWith(SEARCH_AND_FILTER)) {
	res.sendRedirect(req.getContextPath() + DASHBOARD);
	} else {
	res.sendRedirect(req.getContextPath() + DASHBOARD);
	}
	} else if ("user".equals(userRole)) {
			 //User is logged in
	if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
	res.sendRedirect(req.getContextPath() + HOME);
	} else if (uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUT) || uri.endsWith(MENU_ITEMS)
	|| uri.endsWith(CONTACT) || uri.endsWith(RESERVATION) || uri.endsWith(PROFILE)) {
	chain.doFilter(request, response);
	} else if (uri.endsWith(HOME) || uri.endsWith(PAYMENT) || uri.endsWith(ADD_USER)
	|| uri.endsWith(EDIT_USER) || uri.endsWith(DELETE_USER) || uri.endsWith(BOOKING_HISTORY) || uri.endsWith(SEARCH_AND_FILTER)) {
	res.sendRedirect(req.getContextPath() + HOME);
	} else {
	res.sendRedirect(req.getContextPath() + HOME);
	}
	} else {
			 //Not logged in
	if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
	chain.doFilter(request, response);
	} else {
	res.sendRedirect(req.getContextPath() + LOGIN);
	}
	}
	}

	@Override
	public void destroy() {
		 //Cleanup logic, if required
	}
}

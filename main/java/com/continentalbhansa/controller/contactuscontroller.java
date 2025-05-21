package com.continentalbhansa.controller;

import com.continentalbhansa.model.Contact;
import com.continentalbhansa.service.ContactService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ContactController handles POST requests from the contact us form and uses ContactService to save the data.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contactuscontroller" })
public class contactuscontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ContactService contactService;

    /**
     * Initializes the ContactService.
     */
    public contactuscontroller() {
        this.contactService = new ContactService();
    }

    /**
     * Handles GET requests by redirecting to the contact us page.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("ContactUsController: doGet method called");
        request.getRequestDispatcher("/WEB-INF/pages/contactus.jsp").forward(request, response);
    }

    /**
     * Handles POST requests from the contact form to save contact messages.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("ContactUsController: doPost method called");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        Contact contact = new Contact(name, email, message);
        boolean isSaved = contactService.saveContact(contact);

        if (isSaved) {
        	System.out.println("ContactUsController: Message saved successfully");
            request.setAttribute("success", "Your message has been sent successfully!");
        } else {
            request.setAttribute("error", "Failed to send your message. Please try again later.");
        }

        request.getRequestDispatcher("/WEB-INF/pages/contactus.jsp").forward(request, response);
    }
}
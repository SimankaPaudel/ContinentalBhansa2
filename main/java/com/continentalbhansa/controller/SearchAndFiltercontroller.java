package com.continentalbhansa.controller;

import com.continentalbhansa.model.MenuItem;
import com.continentalbhansa.service.SearchAndFilterService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/menu/search")
public class SearchAndFiltercontroller extends HttpServlet {

    private SearchAndFilterService searchAndFilterService;

    @Override
    public void init() throws ServletException {
    	searchAndFilterService = new SearchAndFilterService ();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");

        if (keyword == null) keyword = "";
        if (category == null) category = "All";

        List<MenuItem> filteredItems = searchAndFilterService.searchAndFilterMenuItems(keyword, category);

        request.setAttribute("menuItems", filteredItems);
        request.setAttribute("keyword", keyword);
        request.setAttribute("category", category);

        request.getRequestDispatcher("/viewMenu.jsp").forward(request, response);
    }
}


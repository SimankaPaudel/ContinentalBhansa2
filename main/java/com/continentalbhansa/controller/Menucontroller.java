package com.continentalbhansa.controller;

import com.continentalbhansa.model.MenuItem;
import com.continentalbhansa.service.MenuService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuService menuService;
    
    @Override
    public void init() throws ServletException {
        menuService = new MenuService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        // For admin actions
        if (action != null) {
            switch (action) {
                case "add":
                    request.setAttribute("mode", "add");
                    request.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(request, response);
                    break;
                case "edit":
                    int editId = Integer.parseInt(request.getParameter("id"));
                    MenuItem editItem = menuService.getMenuItemById(editId);
                    request.setAttribute("item", editItem);
                    request.setAttribute("mode", "edit");
                    request.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(request, response);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    menuService.deleteMenuItem(deleteId);
                    response.sendRedirect("MenuController?admin=true");
                    break;
                default:
                    displayMenu(request, response);
                    break;
            }
        } else {
            // For customer view - handle search and filter
            displayMenu(request, response);
        }
    }
    
    private void displayMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("search");
        String categoryFilter = request.getParameter("cuisine");
        
        List<MenuItem> menuItems;
        
        // If search or filter parameters are present, use filtered query
        if ((searchTerm != null && !searchTerm.isEmpty()) || 
            (categoryFilter != null && !categoryFilter.isEmpty())) {
            menuItems = menuService.getFilteredMenuItems(searchTerm, categoryFilter);
        } else {
            // Otherwise get all menu items
            menuItems = menuService.getAllMenuItems();
        }
        
        // Check if this is an admin view or customer view
        if ("true".equals(request.getParameter("admin"))) {
            request.setAttribute("menuItems", menuItems);
            request.getRequestDispatcher("/WEB-INF/pages/admin_menu.jsp").forward(request, response);
        } else {
            request.setAttribute("menuItems", menuItems);
            request.getRequestDispatcher("/WEB-INF/pages/Menu.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        
        // Handle add and update actions
        if ("add".equals(action) || "update".equals(action)) {
            String name = request.getParameter("Dish_Name");
            String description = request.getParameter("Menu_Description");
            double price = Double.parseDouble(request.getParameter("Price"));
            String category = request.getParameter("Category");
            String imagePath = request.getParameter("imagePath");
            
            MenuItem item = new MenuItem();
            item.setName(name);
            item.setDescription(description);
            item.setPrice(price);
            item.setCategory(category);
            item.setImagePath(imagePath);
            
            if ("add".equals(action)) {
                menuService.addMenuItem(item);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("Menu_Items_ID"));
                item.setId(id);
                menuService.updateMenuItem(item);
            }
            
            response.sendRedirect("MenuController?admin=true");
        } else {
            // Handle other form submissions - for example search or filter
            doGet(request, response);
        }
    }
}
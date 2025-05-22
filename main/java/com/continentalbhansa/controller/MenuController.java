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
        if (action == null) action = "list";
//        
        switch (action) {
            case "add":
                request.getRequestDispatcher("/WEB-INF/pages/MenuAdd.jsp").forward(request, response);
                break;

            case "edit":
                int editId = Integer.parseInt(request.getParameter("id"));
                MenuItem editItem = menuService.getMenuItemById(editId);
                request.setAttribute("item", editItem);
                request.getRequestDispatcher("/WEB-INF/pages/MenuEdit.jsp").forward(request, response);
                break;

            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                menuService.deleteMenuItem(deleteId);
                response.sendRedirect("MenuController");
                break;
//
//       s
            default: 
                String searchTerm = request.getParameter("searchTerm");
                String categoryFilter = request.getParameter("category");

                List<MenuItem> items = menuService.getFilteredMenuItems(searchTerm, categoryFilter);
                request.setAttribute("menuItems", items);
                request.setAttribute("searchTerm", searchTerm); // optional: to repopulate the form
                request.setAttribute("categoryFilter", categoryFilter); // optional

                request.getRequestDispatcher("/WEB-INF/pages/Menu.jsp").forward(request, response);
                break;
        }
//                break;/ list
//                List<MenuItem> items = menuService.getAllMenuItems();
//                request.setAttribute("menuItems", items);
//                request.getRequestDispatcher("/WEB-INF/pages/Menu.jsp").forward(request, response);
//                break;
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
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
            int id = Integer.parseInt(request.getParameter("id"));
            item.setId(id);
            menuService.updateMenuItem(item);
        }

        response.sendRedirect("MenuController");
    }
}
    

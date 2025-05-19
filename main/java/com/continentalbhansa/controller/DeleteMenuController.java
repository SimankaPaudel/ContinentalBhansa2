package com.continentalbhansa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.continentalbhansa.config.DBconfig;

@WebServlet("/admin/deleteMenuItem")
public class DeleteMenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteMenuController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdParam = request.getParameter("itemId");
        
        if (itemIdParam != null && !itemIdParam.isEmpty()) {
            try {
                int id = Integer.parseInt(itemIdParam);
                Connection con = DBconfig.getDbConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM menu_items WHERE Menu_Item_ID = ?");
                ps.setInt(1, id);
                
                int result = ps.executeUpdate();
                ps.close();
                con.close();
                
                if (result > 0) {
                    response.sendRedirect(request.getContextPath() + "/admin/manage_menu");
                } else {
                    response.getWriter().println("Failed to delete menu item.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Invalid ID.");
        }
    }
}
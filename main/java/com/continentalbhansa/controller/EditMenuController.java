package com.continentalbhansa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.File;

import com.continentalbhansa.config.DBconfig;

@WebServlet("/admin/saveMenuItem")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 10,  // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class EditMenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditMenuController() {
        super();
    }

    // This method will handle retrieving a menu item's data for AJAX requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        response.setContentType("application/json");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Connection con = DBconfig.getDbConnection();
                
                // Use consistent column name Menu_Items_ID across all controllers
                PreparedStatement ps = con.prepareStatement("SELECT * FROM menu_items WHERE Menu_Items_ID = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Send back JSON response with item details
                    // Make sure to escape quotes in description to prevent JSON syntax errors
                    String description = rs.getString("Menu_Description").replace("\"", "\\\"");
                    String json = "{\"id\":" + rs.getInt("Menu_Items_ID") + 
                                  ",\"name\":\"" + rs.getString("Dish_Name") + 
                                  "\",\"description\":\"" + description + 
                                  "\",\"category\":\"" + rs.getString("Category") + 
                                  "\",\"price\":" + rs.getDouble("Price") + 
                                  ",\"imagePath\":\"" + rs.getString("image_path") + "\"}";
                    
                    response.getWriter().write(json);
                } else {
                    response.getWriter().write("{\"error\":\"Menu item not found.\"}");
                }
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            }
        } else {
            response.getWriter().write("{\"error\":\"Invalid ID.\"}");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdParam = request.getParameter("itemId");
        String category = request.getParameter("category");
        String itemName = request.getParameter("itemName");
        String description = request.getParameter("description");
        String priceParam = request.getParameter("price");
        
        // Debug logs to help identify request parameters
        System.out.println("DEBUG: Received parameters in saveMenuItem:");
        System.out.println("  itemId: " + itemIdParam);
        System.out.println("  category: " + category);
        System.out.println("  itemName: " + itemName);
        System.out.println("  description: " + description);
        System.out.println("  price: " + priceParam);
        
        // Handle both create and update operations
        try {
            double price = Double.parseDouble(priceParam);
            Connection con = DBconfig.getDbConnection();
            
            // Handle file upload
            String imagePath = null;
            Part filePart = request.getPart("itemImage");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = getSubmittedFileName(filePart);
                if (fileName != null && !fileName.isEmpty()) {
                    // Save the file to your upload directory
                    String uploadPath = request.getServletContext().getRealPath("/Resources/Menu/");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();
                    
                    imagePath = fileName;
                    filePart.write(uploadPath + File.separator + fileName);
                    System.out.println("DEBUG: File saved to " + uploadPath + File.separator + fileName);
                }
            }
            
            PreparedStatement ps;
            int result;
            
            if (itemIdParam != null && !itemIdParam.isEmpty() && !itemIdParam.equals("")) {
                // Update existing menu item
                int id = Integer.parseInt(itemIdParam);
                
                if (imagePath != null) {
                    // Update with new image
                    ps = con.prepareStatement(
                        "UPDATE menu_items SET Dish_Name = ?, Menu_Description = ?, Category = ?, Price = ?, image_path = ? WHERE Menu_Items_ID = ?"
                    );
                    ps.setString(1, itemName);
                    ps.setString(2, description);
                    ps.setString(3, category);
                    ps.setDouble(4, price);
                    ps.setString(5, imagePath);
                    ps.setInt(6, id);
                    System.out.println("DEBUG: Updating item with image, ID=" + id);
                } else {
                    // Update without changing image
                    ps = con.prepareStatement(
                        "UPDATE menu_items SET Dish_Name = ?, Menu_Description = ?, Category = ?, Price = ? WHERE Menu_Items_ID = ?"
                    );
                    ps.setString(1, itemName);
                    ps.setString(2, description);
                    ps.setString(3, category);
                    ps.setDouble(4, price);
                    ps.setInt(5, id);
                    System.out.println("DEBUG: Updating item without image, ID=" + id);
                }
            } else {
                // Insert new menu item
                if (imagePath == null) imagePath = "default.jpg"; // Default image if none provided
                
                ps = con.prepareStatement(
                    "INSERT INTO menu_items (Dish_Name, Menu_Description, Category, Price, image_path) VALUES (?, ?, ?, ?, ?)"
                );
                ps.setString(1, itemName);
                ps.setString(2, description);
                ps.setString(3, category);
                ps.setDouble(4, price);
                ps.setString(5, imagePath);
                System.out.println("DEBUG: Inserting new menu item: " + itemName);
            }
            
            result = ps.executeUpdate();
            ps.close();
            con.close();
            
            System.out.println("DEBUG: Query result: " + result + " rows affected");
            
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/manage_menu");
            } else {
                response.getWriter().println("Failed to save menu item.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR in saveMenuItem: " + e.getMessage());
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
    
    // Helper method to extract filename from Part
    private String getSubmittedFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}


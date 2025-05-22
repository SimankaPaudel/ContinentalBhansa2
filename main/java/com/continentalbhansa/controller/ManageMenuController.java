package com.continentalbhansa.controller;

import com.continentalbhansa.model.MenuItem;
import com.continentalbhansa.service.MenuServiceAdmin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet("/admin/manage_menu")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 10,  // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ManageMenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MenuServiceAdmin menuService = new MenuServiceAdmin();
    
    // Directory to store uploaded images relative to web app root
    private static final String UPLOAD_DIRECTORY = "Resources/Menu";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        // Default action is to list all menu items
        if (action == null || action.isEmpty()) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                // Fetch all menu items
                List<MenuItem> allMenuItems = menuService.getAllMenuItems();
                
                // Group menu items by category for the JSP
                req.setAttribute("indianMenuItems", filterByCategory(allMenuItems, "indian"));
                req.setAttribute("chineseMenuItems", filterByCategory(allMenuItems, "chinese"));
                req.setAttribute("italianMenuItems", filterByCategory(allMenuItems, "italian"));
                req.setAttribute("nepaliMenuItems", filterByCategory(allMenuItems, "nepali"));
                req.setAttribute("dessertsMenuItems", filterByCategory(allMenuItems, "desserts"));
                
                req.setAttribute("menuList", allMenuItems); // Keep the full list too
                break;
                
            case "edit":
                // Get the item for editing and forward to form
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    MenuItem item = menuService.getMenuItemById(id);
                    if (item != null) {
                        req.setAttribute("menuItem", item);
                    } else {
                        req.setAttribute("errorMessage", "Menu item not found.");
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("errorMessage", "Invalid ID format.");
                }
                break;
                
            default:
                // No special action needed
                break;
        }

        // Forward to JSP for all GET requests
        req.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        
        if (action == null || action.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/manage_menu");
            return;
        }
        
        switch (action) {
            case "add":
            case "update":
                handleSaveMenuItem(req, resp);
                break;
                
            case "delete":
                handleDeleteMenuItem(req, resp);
                break;
                
            default:
                // Redirect back to menu listing for unknown actions
                resp.sendRedirect(req.getContextPath() + "/admin/manage_menu");
                break;
        }
    }
    
    private void handleSaveMenuItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get upload directory path
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        
        // Get form fields
        String itemIdStr = req.getParameter("itemId");
        String itemName = req.getParameter("itemName");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        String priceStr = req.getParameter("price");
        
        // Validate required fields
        if (itemName == null || description == null || category == null || priceStr == null || 
            itemName.trim().isEmpty() || description.trim().isEmpty() || 
            category.trim().isEmpty() || priceStr.trim().isEmpty()) {
            
            req.setAttribute("errorMessage", "All fields are required");
            req.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(req, resp);
            return;
        }
        
        // Parse price
        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price < 0) {
                req.setAttribute("errorMessage", "Price must be positive");
                req.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(req, resp);
                return;
            }
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Invalid price format");
            req.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(req, resp);
            return;
        }
        
        // Process the image file upload
        Part filePart = req.getPart("itemImage");
        String imagePath = null;
        
        // Check if a file was actually uploaded
        if (filePart != null && filePart.getSize() > 0) {
            // Generate a unique filename to prevent conflicts
            String fileName = UUID.randomUUID().toString() + getFileExtension(filePart);
            
            // Save the file
            try (InputStream fileContent = filePart.getInputStream()) {
                Path targetPath = Paths.get(uploadPath + File.separator + fileName);
                Files.copy(fileContent, targetPath, StandardCopyOption.REPLACE_EXISTING);
                
                // Set the relative path for database storage
                imagePath = fileName;
            } catch (Exception e) {
                req.setAttribute("errorMessage", "Failed to upload image: " + e.getMessage());
                req.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(req, resp);
                return;
            }
        }
        
        // Create MenuItem object
        MenuItem menuItem = new MenuItem();
        menuItem.setName(itemName);
        menuItem.setDescription(description);
        menuItem.setPrice(price);
        menuItem.setCategory(category);
        
        boolean isUpdate = false;
        boolean success;
        
        if (itemIdStr != null && !itemIdStr.trim().isEmpty()) {
            // It's an update operation
            try {
                int itemId = Integer.parseInt(itemIdStr);
                menuItem.setId(itemId);
                isUpdate = true;
                
                // Get existing item to handle the image
                MenuItem existingItem = menuService.getMenuItemById(itemId);
                if (existingItem != null) {
                    // Only update image path if a new image was uploaded
                    if (imagePath != null) {
                        menuItem.setImagePath(imagePath);
                        
                        // Delete old image if it's not the default
                        if (existingItem.getImagePath() != null && 
                            !existingItem.getImagePath().equals("default.jpg")) {
                            File oldImage = new File(uploadPath + File.separator + existingItem.getImagePath());
                            if (oldImage.exists()) {
                                oldImage.delete();
                            }
                        }
                    } else {
                        // Keep the existing image path
                        menuItem.setImagePath(existingItem.getImagePath());
                    }
                    
                    success = menuService.updateMenuItem(menuItem);
                } else {
                    req.setAttribute("errorMessage", "Menu item not found for update");
                    req.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(req, resp);
                    return;
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid item ID");
                req.getRequestDispatcher("/WEB-INF/pages/manage_menu.jsp").forward(req, resp);
                return;
            }
        } else {
            // It's an add operation
            if (imagePath == null) {
                // Use default image if none provided
                imagePath = "default.jpg";
            }
            menuItem.setImagePath(imagePath);
            success = menuService.addMenuItem(menuItem);
        }
        
        if (!success) {
            req.setAttribute("errorMessage", 
                isUpdate ? "Failed to update menu item" : "Failed to add menu item");
        } else {
            req.setAttribute("successMessage", 
                isUpdate ? "Menu item updated successfully" : "Menu item added successfully");
        }
        
        // Redirect back to menu list with the appropriate category
        resp.sendRedirect(req.getContextPath() + "/admin/manage_menu?category=" + category);
    }
    
    private void handleDeleteMenuItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemIdStr = req.getParameter("itemId");
        String category = "indian"; // Default category if not specified
        
        if (itemIdStr != null && !itemIdStr.trim().isEmpty()) {
            try {
                int itemId = Integer.parseInt(itemIdStr);
                
                // Get item details before deletion (for image cleanup and to know category)
                MenuItem itemToDelete = menuService.getMenuItemById(itemId);
                
                if (itemToDelete != null) {
                    category = itemToDelete.getCategory(); // Remember category for redirect
                    
                    boolean success = menuService.deleteMenuItem(itemId);
                    if (success) {
                        // Delete the image file if it's not the default
                        if (itemToDelete.getImagePath() != null && 
                            !itemToDelete.getImagePath().equals("default.jpg")) {
                            
                            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                            File fileToDelete = new File(uploadPath + File.separator + itemToDelete.getImagePath());
                            
                            if (fileToDelete.exists()) {
                                fileToDelete.delete();
                            }
                        }
                        
                        req.setAttribute("successMessage", "Menu item deleted successfully");
                    } else {
                        req.setAttribute("errorMessage", "Failed to delete menu item");
                    }
                } else {
                    req.setAttribute("errorMessage", "Menu item not found");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid item ID");
            }
        } else {
            req.setAttribute("errorMessage", "Item ID is required for deletion");
        }
        
        // Redirect back to menu list with the appropriate category
        resp.sendRedirect(req.getContextPath() + "/admin/manage_menu?category=" + category);
    }
    
    // Helper method to filter menu items by category
    private List<MenuItem> filterByCategory(List<MenuItem> allItems, String category) {
        if (allItems == null) {
            return new ArrayList<>();
        }
        
        return allItems.stream()
                .filter(item -> category.equalsIgnoreCase(item.getCategory()))
                .collect(Collectors.toList());
    }
    
    // Helper method to get file extension from uploaded part
    private String getFileExtension(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
                int dotIndex = fileName.lastIndexOf(".");
                return (dotIndex > 0) ? fileName.substring(dotIndex) : "";
            }
        }
        return ".jpg"; // Default extension
    }
}

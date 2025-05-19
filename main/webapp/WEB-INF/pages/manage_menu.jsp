
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Continental Bhansa | Admin Menu Management</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage_menu.css">

   
   
</head>
<body>

    <header>
        <div class="admin-header">
            <h1>Continental Bhansa</h1>
            <h2>Menu Management System</h2>
        </div>
        <nav class="admin-nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/admindashboard">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/manage_menu" class="active">Menu Management</a></li>
                <li><a href="${pageContext.request.contextPath}/manage_user">Manage User</a></li>
                <li><a href="${pageContext.request.contextPath}/manage_reservation">Reservation</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <!-- Display Messages -->
        <c:if test="${not empty successMessage}">
            <div class="message success-message">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="message error-message">${errorMessage}</div>
        </c:if>
        
        <!-- Add New Item Form -->
        <div class="form-container">
            <h3>${empty menuItem ? 'Add New Menu Item' : 'Edit Menu Item'}</h3>
            <form action="${pageContext.request.contextPath}/admin/manage_menu" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="${empty menuItem ? 'add' : 'update'}">
                <input type="hidden" name="itemId" value="${menuItem.id}">
                
                <div class="form-group">
                    <label for="category">Category:</label>
                    <select id="category" name="category" required>
                        <option value="indian" ${menuItem.category == 'indian' ? 'selected' : ''}>Indian</option>
                        <option value="chinese" ${menuItem.category == 'chinese' ? 'selected' : ''}>Chinese</option>
                        <option value="italian" ${menuItem.category == 'italian' ? 'selected' : ''}>Italian</option>
                        <option value="nepali" ${menuItem.category == 'nepali' ? 'selected' : ''}>Nepali</option>
                        <option value="desserts" ${menuItem.category == 'desserts' ? 'selected' : ''}>Desserts</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="itemName">Item Name:</label>
                    <input type="text" id="itemName" name="itemName" value="${menuItem.name}" required>
                </div>
                
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" required>${menuItem.description}</textarea>
                </div>
                
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" id="price" name="price" step="0.01" min="0" value="${menuItem.price}" required>
                </div>
                
                <div class="form-group">
                    <label for="itemImage">Image:</label>
                    <input type="file" id="itemImage" name="itemImage" accept="image/*">
                    <c:if test="${not empty menuItem.imagePath}">
                        <div class="current-image-container">
                            <p>Current image:</p>
                            <img src="${pageContext.request.contextPath}/Resources/Menu/${menuItem.imagePath}" alt="${menuItem.name}">
                        </div>
                    </c:if>
                </div>
                
                <div class="btn-container">
                    <button type="submit" class="primary-btn">Save Item</button>
                    <a href="${pageContext.request.contextPath}/admin/manage_menu" class="btn">Cancel</a>
                </div>
            </form>
        </div>

        <!-- Category Selection Tabs -->
        <div class="category-tabs">
            <a href="${pageContext.request.contextPath}/admin/manage_menu?category=indian" class="tab-button ${param.category == 'indian' || param.category == null ? 'active' : ''}">Indian</a>
            <a href="${pageContext.request.contextPath}/admin/manage_menu?category=chinese" class="tab-button ${param.category == 'chinese' ? 'active' : ''}">Chinese</a>
            <a href="${pageContext.request.contextPath}/admin/manage_menu?category=italian" class="tab-button ${param.category == 'italian' ? 'active' : ''}">Italian</a>
            <a href="${pageContext.request.contextPath}/admin/manage_menu?category=nepali" class="tab-button ${param.category == 'nepali' ? 'active' : ''}">Nepali</a>
            <a href="${pageContext.request.contextPath}/admin/manage_menu?category=desserts" class="tab-button ${param.category == 'desserts' ? 'active' : ''}">Desserts</a>
        </div>

        <!-- Menu Items Table for Active Category -->
        <div class="category-content active">
            <h3 class="section-title">
                <c:choose>
                    <c:when test="${param.category == 'chinese'}">Chinese Cuisine</c:when>
                    <c:when test="${param.category == 'italian'}">Italian Cuisine</c:when>
                    <c:when test="${param.category == 'nepali'}">Nepali Cuisine</c:when>
                    <c:when test="${param.category == 'desserts'}">Desserts</c:when>
                    <c:otherwise>Indian Cuisine</c:otherwise>
                </c:choose>
            </h3>
            
            <table class="menu-table">
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${param.category == 'chinese'}">
                            <c:set var="itemList" value="${chineseMenuItems}" />
                        </c:when>
                        <c:when test="${param.category == 'italian'}">
                            <c:set var="itemList" value="${italianMenuItems}" />
                        </c:when>
                        <c:when test="${param.category == 'nepali'}">
                            <c:set var="itemList" value="${nepaliMenuItems}" />
                        </c:when>
                        <c:when test="${param.category == 'desserts'}">
                            <c:set var="itemList" value="${dessertsMenuItems}" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="itemList" value="${indianMenuItems}" />
                        </c:otherwise>
                    </c:choose>
                    
                    <c:forEach var="item" items="${itemList}">
                        <tr>
                            <td><img src="${pageContext.request.contextPath}/${item.imagePath}" alt="${item.name}" class="thumbnail"></td>
                            <td>${item.name}</td>
                            <td>${item.description}</td>
                            <td>$${item.price}</td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/admin/manage_menu?action=edit&id=${item.id}" class="edit-btn">Edit</a>
                                <form action="${pageContext.request.contextPath}/admin/manage_menu" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="itemId" value="${item.id}">
                                    <button type="submit" class="delete-btn" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>


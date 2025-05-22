<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.continentalbhansa.model.User" %>
<%
    User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Edit User | Continental Bhansa</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/AddUser.css">
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

    <div class="layout">
        <!-- Sidebar -->
        <div class="sidebar">
            <h4 class="brand">Continental Bhansa</h4>
            <a href="${pageContext.request.contextPath}/"><i class="fas fa-folder"></i> Dashboard</a>
            <a href="${pageContext.request.contextPath}/manage_reservation"><i class="fas fa-calendar-alt"></i> Reservations</a>
            <a href="#" class="active"><i class="fas fa-users"></i> Users</a>
            <a href="${pageContext.request.contextPath}/manage_menu"><i class="fas fa-bars"></i> Menu</a>
            <div class="logout-section">
                <a href="#"><i class="fas fa-sign-out-alt"></i> Logout</a>
            </div>
        </div>

        <!-- Main Content -->
        <div class="main">
            <div class="form-wrapper">
                <div class="form-container">
                    <h2>Edit User</h2>
                    <form action="EditUserController" method="post">
                        <input type="hidden" name="id" value="<%= user.getId() %>">

                        <label for="name">Username</label>
                        <input type="text" name="username" id="name" required value="<%= user.getUserName() %>">

                        <label for="email">Email</label>
                        <input type="email" name="email" id="email" required value="<%= user.getEmail() %>">

                        <label for="phone">Phone Number</label>
                        <input type="text" name="phone" id="phone" required value="<%= user.getPhoneNumber() %>">

                        <label for="address">Address</label>
                        <input type="text" name="address" id="address" required value="<%= user.getAddress() %>">

                        <button type="submit" class="btn-submit">
                            <i class="fas fa-user-edit"></i> Update User
                        </button>
                        <a href="manage_user" style="text-decoration:none;" class="btn-back">
                            <i class="fas fa-arrow-left"></i> Go Back
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

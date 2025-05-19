<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Profile | Continental Bhansa</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

    <!-- Navbar -->
    <%@ include file="navbar.jsp" %>
    <!-- Profile Content -->
    <div class="profile-container">
        <div class="profile-box">
            <h2><i class="fas fa-user-circle"></i> My Profile</h2>
            <form>
                <label>Full Name</label>
                <input type="text" value="Prisha Malla" readonly>

                <label>Email</label>
                <input type="email" value="prishamalla@gmail.com" readonly>

                <label>Phone Number</label>
                <input type="text" value="+977 9860889546" readonly>

                <label>Address</label>
                <input type="text" value="Baluwatar, Kathmandu" readonly>

                <button type="button"><i class="fas fa-edit"></i> Edit Profile</button>
            </form>
        </div>
    </div>

    <!-- Footer -->
    

            <%@ include file="Footer.jsp" %>

</body>
</html>

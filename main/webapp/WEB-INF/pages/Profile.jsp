<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>My Profile | Continental Bhansa</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Profile.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

	<%@ include file="navbar.jsp"%>

	<!-- Profile Content -->
	<div class="profile-container">
		<div class="profile-box">
			<h2>
				<i class="fas fa-user-circle"></i> My Profile
			</h2>
			<c:if test="${not empty error}">
				<div class="error-message" style="color: red">${error}</div>
			</c:if>
			<c:if test="${not empty success}">
				<div class="error-message">${success}</div>
			</c:if>
			<form action="Profilecontroller" method="post">
				<label>User Name </label>
				<h5 style="color: red;">(You cannot change your username)</h5>
				<input type="text" value="${user.userName }" name="username"
					readonly> <label>Email</label> <input type="email"
					value="${user.email}" name="email"> <label>Phone
					Number</label> <input type="text" value="${user.phoneNumber}" name="phone">
				<label>Address</label> <input type="text" value="${user.address}"
					name="address">
				<%
				String successMessage = (String) request.getAttribute("successMessage");
				%>
				<%
				if (successMessage != null) {
				%>
				<p style="color: green;"><%=successMessage%></p>
				<%
				}
				%>
				<%
				String errorMessage = (String) request.getAttribute("errorMessage");
				%>
				<%
				if (errorMessage != null) {
				%>
				<p style="color: red;"><%=errorMessage%></p>
				<%
				}
				%>
				<c:if test="${not empty error}">

				</c:if>
				<button type="submit">Update Profile</button>
			</form>

		</div>
	</div>

	<!-- Footer -->
	<footer>
		<div class="footer-container">
			<div class="footer-about">
				<h3>Continental Bhansa</h3>
				<p>Experience authentic flavors with a contemporary twist. Our
					chefs craft each dish with passion, tradition, and the finest
					ingredients.</p>
				<div class="social-icons">
					<i class="fab fa-facebook-f"></i> <i class="fab fa-instagram"></i>
					<i class="fab fa-twitter"></i>
				</div>
			</div>

			<div class="footer-links">
				<h4>Quick Links</h4>
				<ul>
					<li>Home</li>
					<li>Menu</li>
					<li>Reservations</li>
					<li>Profile</li>
					<li>Contact Us</li>

				</ul>
			</div>

			<div class="footer-contact">
				<h4>Contact Us</h4>
				<p>
					<i class="fas fa-map-marker-alt"></i> Kamalpokhari, Kathmandu
				</p>
				<p>
					<i class="fas fa-phone"></i> +977 9803421026
				</p>
				<p>
					<i class="fas fa-envelope"></i> info@continentalbhansa.com
				</p>
			</div>
		</div>
		<div class="footer-bottom">© 2025 Continental Bhansa. All rights
			reserved.</div>
	</footer>

</body>
</html>

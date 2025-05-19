<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
</head>
<body>
	<header class="navbar">
    <div class="font-logo">Continental Bhansa</div>
    <nav class="nav-links">
      <a href="${pageContext.request.contextPath}/">Home</a>
      <a href="${pageContext.request.contextPath}/Logincontroller">Login</a>
      <a href="${pageContext.request.contextPath}/contactuscontroller">Contact Us</a>
      <a href="${pageContext.request.contextPath}/registercontroller">Register</a>
      <a href="${pageContext.request.contextPath}/Reservationcontroller">Book a Table</a>
      <a href="${pageContext.request.contextPath}/Profilecontroller">Profile</a>
      <a href="${pageContext.request.contextPath}/MenuController">Menu</a>
      <form action="${pageContext.request.contextPath}/logout" method="post">
			<input type="submit" class="nav-button" value="Logout" />
		</form>
      
    </nav>
  </header>

</body>
</html>
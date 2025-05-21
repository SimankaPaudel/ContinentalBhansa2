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
	
	<%
	// Initialize necessary objects and variables
	HttpSession userSession = request.getSession(false);
	String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
	// need to add data in attribute to select it in JSP code using JSTL core tag
	pageContext.setAttribute("currentUser", currentUser);
	%>
	<header class="navbar">
    <div class="font-logo">Continental Bhansa</div>
    <nav class="nav-links">
      <a href="${pageContext.request.contextPath}/">Home</a>
  
      <a href="${pageContext.request.contextPath}/contactuscontroller">Contact Us</a>
      <a href="${pageContext.request.contextPath}/registercontroller">Register</a>
      <a href="${pageContext.request.contextPath}/BookTable">Book a Table</a>
      <a href="${pageContext.request.contextPath}/Profilecontroller">Profile</a>
      <a href="${pageContext.request.contextPath}/MenuController">Menu</a>
      <a href="${pageContext.request.contextPath}/aboutuscontroller">About Us</a>
      <c:choose>
					<c:when test="${not empty currentUser}">
						<form action="${pageContext.request.contextPath}/logout" method="post">
							<input type="submit" class="nav-button" value="Logout" />
						</form>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/Logincontroller">Login</a>
					</c:otherwise>
		</c:choose></li>
	
    </nav>
  </header>

</body>
</html>
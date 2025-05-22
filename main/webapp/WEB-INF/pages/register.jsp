<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>User Register - Continental Bhansa</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&family=Inter&display=swap" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css?family=Dancing+Script" rel="stylesheet" />
</head>

<body>
  <!-- Navbar -->
  <header class="navbar">
    <div class="font-logo">Continental Bhansa</div>
    <nav class="nav-links">
      <a href="UserDashboard.html">Home</a>
      <a href="contactus.html">Contact Us</a>
      <a href="#">Login</a>
      
    </nav>
  </header>

  <!-- Banner -->
  <section class="banner">
    <h1>Create Account</h1>
    <p>Join us to manage your reservations and receive exclusive offers</p>
  </section>

  <!-- Registration Form -->
  <section class="form-section">
    <div class="form-card">
      <h2>Create Account</h2>
      <p>Fill in the details below to get started</p>
      <c:if test="${not empty error}">
							 	<div class="error-message" style="color:red">${error}</div>
		</c:if>
		<c:if test="${not empty success}">
							 	<div class="error-message">${success}</div>
		</c:if>
      <form action="registercontroller" method="post">
        <div class="form-row dual">
          <input type="text" placeholder="Username" name="username" value="${username}"required />
        </div>
        <div class="form-row dual">
          <input type="email" placeholder="Email" name="email" value="${email}" required />
          <input type="text" style="margin-top:12px" placeholder="Address" name="address" value="${address}" required />
        </div>
        <div class="form-row dual">
          <input type="tel" placeholder="Phone Number" name="phoneNumber" value="${phoneNumber}" required />
        </div>
        <div class="form-row dual">
          <input type="password" placeholder="Password" name="password" required />
        </div>
        <div class="form-row dual">
         <input type="password" placeholder="Confirm Password" name="reTypePassword" required />
        </div>
        <label class="checkbox">
          <input type="checkbox" required />
          I agree to the <a href="#">Terms and Conditions</a> and <a href="#">Privacy Policy</a>
        </label>
        <button type="submit" class="submit-btn">Create Account</button>
        <p class="signin-text">Already have an account? <a href="#">Sign in</a></p>
      </form>
    </div>
  </section>

  <!-- Footer -->
  <footer class="footer">
    <div class="footer-col about">
      <h4>Continental Bhansa</h4>
      <p>
        Experience authentic Indian flavors with a contemporary twist. Our chefs craft each dish with passion, tradition, and the finest ingredients.
      </p>
    </div>
    <div class="footer-col links">
      <h4>Quick Links</h4>
      <ul>
        <li><a href="#">Home</a></li>
        <li><a href="#">Login</a></li>
        <li><a href="#">Register</a></li>
      </ul>
    </div>
    <div class="footer-col contact">
      <h4>Contact Us</h4>
      <p>123 Spice Avenue, Culinary District, Flavorville, FL 54321</p>
      <p>+1 (543) 123-4567</p>
      <p>info@continentalbhansa.com</p>
    </div>
  </footer>

  <div class="copyright">
    &copy; 2025 Continental Bhansa. All rights reserved.
  </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <%@ include file="navbar.jsp" %>
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
      <form>
        <div class="form-row dual">
          <input type="text" placeholder="First Name" required />
          <input type="text" placeholder="Last Name" required />
        </div>
        <div class="form-row">
          <input type="email" placeholder="Email Address" required />
        </div>
        <div class="form-row">
          <input type="tel" placeholder="Phone Number" required />
        </div>
        <div class="form-row dual">
          <input type="password" placeholder="Password" required />
          <input type="password" placeholder="Confirm Password" required />
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
  <%@ include file="Footer.jsp" %>
  
</body>
</html>

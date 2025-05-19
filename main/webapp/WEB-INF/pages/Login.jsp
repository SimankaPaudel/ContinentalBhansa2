<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Login - Continental Bhansa</title>
  
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Login.css">
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
</head>
<body>

  <!-- Homepage Style Navbar -->
  <%@ include file="navbar.jsp" %>

  <!-- Banner -->
  <section class="banner">
    <h1>Login</h1>
    <p>Access your dashboard</p>
  </section>

  <!-- Login Form Section -->
  <main class="form-section">
    <div class="form-card">
      <h2>Welcome Back</h2>
      <p>Please enter your credentials to proceed</p>
      <form action="Logincontroller" method="post">
        <input type="text" placeholder="username" class="username" name="username" required />
        <input type="password" placeholder="Password" class="password" name="password" required />
        <button type="submit" class="submit-btn">Login</button>
      </form>
    </div>
  </main>

  <!-- Footer -->
  <%@ include file="Footer.jsp" %>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>About Us - Continental Bhansa</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutus.css">
</head>
<body>

<%@ include file="navbar.jsp" %>


<!-- Page Content -->
<div class="container">
  <section class="intro-section">
    <h1>Our Story</h1>
    <p class="intro">Continental Bhansa isn't just a restaurant — it's a love letter to comfort food with a continental twist, served in the heart of our hometown.</p>
    <p> We spent months experimenting with recipes, curating the perfect ambiance, and shaping a restaurant that speaks to the soul.</p>
    <p>Continental Bhanchaa officially opened its doors in 2024, and since then, it has been a place of gathering, celebration, and unforgettable taste experiences. Every dish tells a story, every table holds a memory, and every guest becomes a part of our journey.</p>
  </section>

  <section class="story">
    <h2>Where Taste Meets Heart</h2>
    <p>
      Born from late-night coffee talks and shared dreams, <strong>Continental Bhansa</strong> was envisioned by five close friends who believed food could do more than satisfy hunger — it could spark connection. 
    </p>
    <p>
      Drawing subtle inspiration from cozy urban cafés, we brought together warm interiors, communal tables, and hearty European flavors to offer a home away from home. Whether it's brunch with friends or a solo evening meal, our space is designed to make every guest feel like they belong.
    </p>
  </section>

  <section class="team">
    <h2>Meet the Founders</h2>
    <div class="team-grid">
      <div class="member">
        <img src="${pageContext.request.contextPath}/Resources/about us/Abhigya.jpeg" alt="Abhigya Pun">
        <h3>Abhigya Pun</h3>
        <p>Co-founder & Executive Chef</p>
      </div>
      <div class="member">
        <img src="${pageContext.request.contextPath}/Resources/about us/Ashim.jpg" alt="Ashim Khadgi">
        <h3>Ashim Khadgi</h3>
        <p>Operations </p>
      </div>
      <div class="member">
        <img src="${pageContext.request.contextPath}/Resources/member3.jpg" alt="Prasiddha Bhattarai">
        <h3>Prasiddha Bhattarai</h3>
        <p>Finance & Strategy</p>
      </div>
      <div class="member">
        <img src="${pageContext.request.contextPath}/Resources/about us/prisha.jpeg" alt="Prisha Malla">
        <h3>Prisha Malla</h3>
        <p>Brand & Marketing</p>
      </div>
      <div class="member">
        <img src="${pageContext.request.contextPath}/Resources/about us/Simanka.jpeg" alt="Simanka Paudel">
        <h3>Simanka Paudel</h3>
        <p>Technology & Systems</p>
      </div>
    </div>
  </section>

  <section class="mission">
    <h2>Our Vision</h2>
    <p>To create an inclusive dining space where stories are shared over slow-cooked meals and soulful conversations — a place that celebrates people as much as it does food.</p>
  </section>
</div>
<!-- ... your existing About Us content ... -->

<!-- Full Footer Section (like UserDashboard) -->
<%@ include file="Footer.jsp" %>





</body>
</html>
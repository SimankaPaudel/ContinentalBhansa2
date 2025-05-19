<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Contact Us - Continental Bhanchaa</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/contactus.css">
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display&family=Segoe+UI&display=swap"
	rel="stylesheet">
</head>
<body>

	<!-- Header (Same as UserDashboard) -->
	<%@ include file="navbar.jsp"%>

	<!-- Hero Section -->
	<div class="hero">
		<h1>Contact Us</h1>
	</div>

	<!-- Contact Form and Info -->
	<div class="contact-section">
		<div class="form-container">
			<h2>Send Us a Message</h2>
			<form action="${pageContext.request.contextPath}/contactuscontroller"
				method="post">
				<input type="text" name="name" placeholder="Your Name" required>
				<input type="email" name="email" placeholder="Your Email" required>
				<textarea name="message" rows="5" placeholder="Your Message"
					required></textarea>
				<button type="submit">Send Message</button>
			</form>
		</div>

		<div class="info-container">
			<h2>Visit Us</h2>
			<p>
				<strong>Address:</strong> Kamal Pokhari, Kathmandu, Nepal
			</p>
			<p>
				<strong>Phone:</strong> <a href="tel:+1234567890">+977
					1234567890</a>
			</p>
			<p>
				<strong>Email:</strong> <a
					href="mailto:info@continentalbhanchaa.com">info@continentalbhanchaa.com</a>
			</p>

			<h3>Opening Hours</h3>
			<ul>
				<li>Mon–Fri: 10am – 9pm</li>
				<li>Sat–Sun: 9am – 10pm</li>
			</ul>

			<h3>Find Us</h3>
			<iframe
				src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3531.8032300000003!2d85.3205817!3d27.708317!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb190e75b94a0b%3A0xe2b9edb8c9ac0b38!2sKamal%20Pokhari%2C%20Kathmandu%2C%20Nepal!5e0!3m2!1sen!2snp!4v1680462769490"
				width="100%" height="200" style="border: 0;" allowfullscreen=""
				loading="lazy"> </iframe>

			<div class="social-media">
				<a href="https://facebook.com" target="_blank">Facebook</a> <a
					href="https://instagram.com" target="_blank">Instagram</a> <a
					href="https://twitter.com" target="_blank">Twitter</a>
			</div>
		</div>
	</div>

	<!-- Full Footer Section (like UserDashboard) -->
	<%@ include file="Footer.jsp" %>

	<!-- Popup success alert -->
	<c:if test="${not empty success}">
		<script>
			alert("${success}");
		</script>
	</c:if>


</body>
</html>

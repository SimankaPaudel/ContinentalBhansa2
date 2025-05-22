<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Book a table at Continental Bhaansa - authentic Indian cuisine with a contemporary twist">
    <title>Book A Table - Continental Bhansa</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/BookTable.css">
    <link rel="stylesheet" href="navbar.css">
    <link rel="stylesheet" href="footer.css">
</head>
<body>
    <!-- Header Navigation -->
    <header class="header">
        <a href="#" class="logo">
            <div class="logo-text">Continental <span>Bhansa</span></div>
        </a>
        <button class="menu-btn" id="menuBtn"><i class="fas fa-bars"></i></button>
        <ul class="nav-links" id="navLinks">
            <li><a href="UserDashboard.html"><i class="fas fa-home"></i> Home</a></li>
            <li><a href="Menu.html"><i class="fas fa-utensils"></i> Menu</a></li>
            <li><a href="bookinghistory.html"><i class="fas fa-calendar-alt"></i> Bookings</a></li>
            <li><a href="Login.html"><i class="fas fa-user"></i> Login</a></li>
            <li><a href="register.html"><i class="fas fa-user-plus"></i> Register</a></li>
        </ul>
    </header>
    
    <!-- Hero Section -->
    <section class="hero-section">
        <p class="section-subtitle">Make a Reservation</p>
        <h1 class="section-title">Continental <span>Bhansa</span></h1>
        <p class="section-description">Experience the exotic flavors of India with our contemporary twist on traditional recipes</p>
    </section>
    
    <div class="container">
        <div class="reservation-container">
            <!-- Reservation Form Section -->
            <div class="reservation-form">
                <h2 class="form-title">Book Your Table</h2>
                
                <!-- Success message display area -->
                <% if(request.getAttribute("confirmation") != null) { %>
                <div class="confirmation-message" style="display: block; color: green; background-color: #d4edda; border: 1px solid #c3e6cb; padding: 15px; margin-bottom: 15px; border-radius: 5px;">
                    <%= request.getAttribute("confirmation") %>
                </div>
                <% } %>
                
                <!-- Error message display area -->
                <% if(request.getAttribute("error") != null) { %>
                <div class="error-message" style="display: block; color: #721c24; background-color: #f8d7da; border: 1px solid #f5c6cb; padding: 15px; margin-bottom: 15px; border-radius: 5px;">
                    <%= request.getAttribute("error") %>
                </div>
                <% } %>
                
                <form id="reservationForm" method="post" action="BookTable">
                    <!-- Hidden fields for processing -->
                    <input type="hidden" name="reservationId" value="0">
                    <input type="hidden" name="status" value="PENDING">
                    <input type="hidden" name="userId" value="1">
                    <input type="hidden" name="tableId" value="1">
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label class="form-label" for="name">Full Name</label>
                            <input type="text" class="form-input" id="name" name="name" required>
                        </div>
                        
                        <div class="form-group">
                            <label class="form-label" for="numberOfPeople">Number of Guests</label>
                            <input type="number" class="form-input" id="numberOfPeople" name="numberOfPeople" min="1" max="20" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label class="form-label" for="email">Email Address</label>
                            <input type="email" class="form-input" id="email" name="email" required>
                        </div>
                        
                        <div class="form-group">
                            <label class="form-label" for="phone">Contact Number</label>
                            <input type="tel" class="form-input" id="phone" name="contactNumber" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label class="form-label" for="reservationDate">Date</label>
                            <input type="date" class="form-input" id="reservationDate" name="reservationDate" required>
                        </div>
                        
                        <div class="form-group">
                            <label class="form-label" for="reservationTime">Time</label>
                            <input type="time" class="form-input" id="reservationTime" name="reservationTime" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group special-request">
                            <label class="form-label" for="specialRequest">Special Request</label>
                            <textarea class="form-input" id="specialRequest" name="specialRequest" placeholder="Let us know if you have any special requirements..."></textarea>
                        </div>
                    </div>
                    
                    <button type="submit" class="submit-btn">Confirm Reservation</button>
                </form>
            </div>
            
            <!-- Location Information Section -->
            <div class="location-info">
                <div class="location-card">
                    <div class="location-image">
                        <div class="image-placeholder">
                            <i class="fas fa-map-marker-alt"></i>
                        </div>
                    </div>
                    <h3 class="location-title">Our Location</h3>
                    <p class="location-address">123 Archer Avenue, Coliseum District, Gainesville, FL 32611</p>
                    <div class="location-contact">
                        <div class="contact-item"><i class="fas fa-phone"></i> +1 (352) 123-4567</div>
                        <div class="contact-item"><i class="fas fa-envelope"></i> info@continentalbhansa.com</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Footer -->
    <footer class="footer">
        <div class="footer-container">
            <div class="footer-info">
                <div class="footer-logo">
                    <div class="logo-text">Continental <span>Bhansa</span></div>
                </div>
                <p class="footer-description">Experience authentic Indian flavors with a contemporary twist. Our chefs craft each dish using organic, traditional spices and fresh ingredients.</p>
                <div class="footer-social">
                    <a href="#"><i class="fab fa-facebook-f"></i></a>
                    <a href="#"><i class="fab fa-instagram"></i></a>
                    <a href="#"><i class="fab fa-twitter"></i></a>
                </div>
            </div>
            
            <div class="footer-links">
                <h4>Quick Links</h4>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Menu</a></li>
                    <li><a href="#">Reservations</a></li>
                    <li><a href="#">Register</a></li>
                </ul>
            </div>
            
            <div class="footer-contact">
                <h4>Contact Us</h4>
                <p>Kamalpokhari, Kathmandu</p>
                <p>+977 9803421026</p>
                <p>info@continentalbhansa.com</p>
            </div>
        </div>
        <div class="copyright">© 2025 Continental Bhansa. All rights reserved.</div>
    </footer>
    
    <script>
        // Mobile menu toggle
        document.getElementById('menuBtn').addEventListener('click', function() {
            document.getElementById('navLinks').classList.toggle('active');
        });
        
        // Set minimum date to today
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('reservationDate').setAttribute('min', today);
        
        // Form submission validation
        document.getElementById('reservationForm').addEventListener('submit', function(e) {
            // Basic form validation
            const name = document.getElementById('name').value;
            const email = document.getElementById('email').value;
            const guests = document.getElementById('numberOfPeople').value;
            const date = document.getElementById('reservationDate').value;
            const time = document.getElementById('reservationTime').value;
            const phone = document.getElementById('phone').value;
            
            if (!name || !email || !guests || !date || !time || !phone) {
                e.preventDefault();
                alert("Please fill in all required fields.");
                return false;
            }
            
            // Additional validation
            if (guests < 1 || guests > 20) {
                e.preventDefault();
                alert("Number of guests must be between 1 and 20.");
                return false;
            }
            
            // Show loading message
            const submitBtn = document.querySelector('.submit-btn');
            submitBtn.textContent = 'Processing...';
            submitBtn.disabled = true;
            
            return true;
        });
    </script>
</body>
</html>
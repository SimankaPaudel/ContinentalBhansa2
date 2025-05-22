<%@ page language="java" contentType="text/html; charset=UTF-8" 

pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="description" content="Book a table at Continental Bhaansa - authentic Indian cuisine with a contemporary twist">

    <title>Book A Table - Continental Bhansa</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link rel="stylesheet" href="BookTable.css">

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

                

                <form id="reservationForm" method="post">

                    <div class="form-row">

                        <div class="form-group">

                            <label class="form-label" for="name">Full Name</label>

                            <input type="text" class="form-input" id="name" name="name" required>

                        </div>

                        

                        <div class="form-group">

                            <label class="form-label" for="guests">Number of Guests</label>

                            <input type="number" class="form-input" id="guests" name="guests" min="1" max="20" required>

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

                            <label class="form-label" for="date">Date</label>

                            <input type="date" class="form-input" id="date" name="date" required>

                        </div>

                        

                        <div class="form-group">

                            <label class="form-label" for="time">Time</label>

                            <input type="time" class="form-input" id="time" name="time" required>

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

                

                <div id="confirmationMessage" class="confirmation-message" style="display: none;">

                    Your reservation has been successfully submitted! We'll send a confirmation to your email shortly.

                </div>

                

                <div id="errorMessage" class="error-message" style="display: none;">

                    There was a problem processing your reservation. Please try again.

                </div>

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

            

            <div class= "footer-contact">

                <h4>Contact Us</h4>

                <p> Kamalpokhari, Kathmandu</p>

                <p> +977 9803421026</p>

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

        

        // Form submission

        document.getElementById('reservationForm').addEventListener('submit', function(e) {

            e.preventDefault();

            

            // Basic form validation

            const name = document.getElementById('name').value;

            const email = document.getElementById('email').value;

            const guests = document.getElementById('guests').value;

            const date = document.getElementById('date').value;

            const time = document.getElementById('time').value;

            

            if (!name || !email || !guests || !date || !time) {

                document.getElementById('errorMessage').style.display = 'block';

                document.getElementById('confirmationMessage').style.display = 'none';

                return;

            }

            

            // Here you would typically send the data to your server

            // For demo purposes, we'll just show the confirmation message

            document.getElementById('confirmationMessage').style.display = 'block';

            document.getElementById('errorMessage').style.display = 'none';

            document.getElementById('reservationForm').reset();

            

            // Scroll to confirmation message

            document.getElementById('confirmationMessage').scrollIntoView({

                behavior: 'smooth'

            });

        });

        

        // Set minimum date to today

        const today = new Date().toISOString().split('T')[0];

        document.getElementById('date').setAttribute('min', today);

    </script>

</body>

</html>
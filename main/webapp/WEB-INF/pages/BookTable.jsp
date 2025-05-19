<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Book a table at Continental Bhaansa - authentic Indian cuisine with a contemporary twist">
    <title>Book A Table - Continental Bhaansa</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/BookTable.css">
    <style>
        .button-container {
            display: flex;
            gap: 15px;
            margin-top: 20px;
            justify-content: center;
        }

        .view-reservations-btn {
            padding: 12px 24px;
            background-color: #4a6741;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-size: 16px;
            transition: background-color 0.3s ease;
            display: inline-block;
            text-align: center;
        }

        .view-reservations-btn:hover {
            background-color: #3a5233;
        }

        .submit-btn {
            padding: 12px 24px;
            background-color: #e67e22;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #d35400;
        }
    </style>
</head>
<body>
	<%@ include file="navbar.jsp" %>
    
    
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
                    
                    <div class="button-container">
                        <button type="submit" class="submit-btn">Confirm Reservation</button>
                        <a href="reservation.jsp" class="view-reservations-btn">View Reservations</a>
                    </div>
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
    <%@ include file="Footer.jsp" %>
    
    <script>
        // Mobile menu toggle
        document.getElementById('menuBtn').addEventListener('click', function() {
            document.getElementById('navLinks').classList.toggle('active');
        });
        
        // Set minimum date to today
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('date').setAttribute('min', today);
        
        // Form submission
        document.getElementById('reservationForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Hide any existing messages
            document.getElementById('errorMessage').style.display = 'none';
            document.getElementById('confirmationMessage').style.display = 'none';
            
            // Basic form validation
            const name = document.getElementById('name').value.trim();
            const email = document.getElementById('email').value.trim();
            const guests = document.getElementById('guests').value.trim();
            const date = document.getElementById('date').value.trim();
            const time = document.getElementById('time').value.trim();
            const phone = document.getElementById('phone').value.trim();
            const specialRequest = document.getElementById('specialRequest').value.trim();
            
            if (!name || !email || !guests || !date || !time || !phone) {
                showError('Please fill in all required fields.');
                return;
            }
            
            // Validate number of guests
            const numGuests = parseInt(guests);
            if (isNaN(numGuests) || numGuests < 1 || numGuests > 20) {
                showError('Number of guests must be between 1 and 20');
                return;
            }
            
            // Create form data
            const formData = new FormData();
            formData.append('name', name);
            formData.append('email', email);
            formData.append('guests', guests);
            formData.append('date', date);
            formData.append('time', time);
            formData.append('contactNumber', phone);
            formData.append('specialRequest', specialRequest);
            
            // Disable submit button while processing
            const submitBtn = document.querySelector('.submit-btn');
            submitBtn.disabled = true;
            submitBtn.style.opacity = '0.7';
            
            // Send AJAX request
            fetch('${pageContext.request.contextPath}/Reservationcontroller', {
                method: 'POST',
                body: new URLSearchParams(formData)
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    showSuccess(data.message);
                    document.getElementById('reservationForm').reset();
                } else {
                    showError(data.message);
                }
            })
            .catch(error => {
                showError('An error occurred. Please try again.');
                console.error('Error:', error);
            })
            .finally(() => {
                // Re-enable submit button
                submitBtn.disabled = false;
                submitBtn.style.opacity = '1';
            });
        });
        
        function showError(message) {
            const errorDiv = document.getElementById('errorMessage');
            errorDiv.textContent = message;
            errorDiv.style.display = 'block';
            document.getElementById('confirmationMessage').style.display = 'none';
            // Scroll error into view
            errorDiv.scrollIntoView({ behavior: 'smooth', block: 'center' });
        }
        
        function showSuccess(message) {
            const confirmDiv = document.getElementById('confirmationMessage');
            confirmDiv.textContent = message;
            confirmDiv.style.display = 'block';
            document.getElementById('errorMessage').style.display = 'none';
            // Scroll confirmation into view
            confirmDiv.scrollIntoView({ behavior: 'smooth', block: 'center' });
        }
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Management - Continental Bhansa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Reservation.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <main>
        <div class="app-container">
            <div class="app-header">
                <h1 class="app-title">Reservation Management</h1>
                <p class="app-subtitle">Find, modify or cancel your dining reservations</p>
            </div>
            
            <div class="tab-container">
                <button class="tab-button active" data-tab="find">Find Reservation</button>
                <button class="tab-button" data-tab="cancel">Cancel Reservation</button>
                <button class="tab-button" data-tab="edit">Edit Reservation</button>
            </div>
            
            <div id="find-tab" class="tab-content active">
                <div class="form-row">
                    <label class="form-label" for="find-reservation-id">Find your reservation</label>
                    <div class="form-group">
                        <div class="form-field">
                            <input type="text" class="form-input" id="find-reservation-id" placeholder="Enter your reservation ID">
                        </div>
                        <div class="form-field">
                            <input type="email" class="form-input" id="find-email" placeholder="Enter your email address">
                        </div>
                    </div>
                    <div class="form-center">
                        <button id="find-button" class="btn btn-primary">Search</button>
                    </div>
                </div>
                
                <div id="search-message" class="search-message"></div>
                
                <div id="reservation-details" class="reservation-details">
                    <h3>Your Reservation Details</h3>
                    <div class="detail-row">
                        <div class="detail-label">Name:</div>
                        <div class="detail-value" id="detail-name">Rajiv Mehta</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">Email:</div>
                        <div class="detail-value" id="detail-email">rajiv@example.com</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">Phone:</div>
                        <div class="detail-value" id="detail-phone">555-789-1234</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">Reservation ID:</div>
                        <div class="detail-value" id="detail-id">BHNS22789</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">Date:</div>
                        <div class="detail-value" id="detail-date">April 25, 2025</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">Time:</div>
                        <div class="detail-value" id="detail-time">7:30 PM</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">Guests:</div>
                        <div class="detail-value" id="detail-guests">4</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">Table Type:</div>
                        <div class="detail-value" id="detail-table">Private Dining</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">Special Requests:</div>
                        <div class="detail-value" id="detail-requests">Birthday celebration, window seating if possible</div>
                    </div>
                    
                    <div class="action-row">
                        <button id="go-to-edit" class="btn btn-primary">Edit Reservation</button>
                        <button id="go-to-cancel" class="btn btn-secondary">Cancel Reservation</button>
                    </div>
                </div>
            </div>
            
            <div id="cancel-tab" class="tab-content">
                <form id="cancellationForm">
                    <div class="form-row">
                        <label class="form-label" for="cancel-name">Name</label>
                        <div class="form-group">
                            <div class="form-field">
                                <input type="text" class="form-input" id="cancel-first-name" name="firstName" placeholder="First name" required>
                            </div>
                            <div class="form-field">
                                <input type="text" class="form-input" id="cancel-last-name" name="lastName" placeholder="Last name" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <div class="form-field">
                                <label class="form-label" for="cancel-phone">Phone</label>
                                <input type="tel" class="form-input" id="cancel-phone" name="phone" placeholder="Phone number" required>
                            </div>
                            <div class="form-field">
                                <label class="form-label" for="cancel-email">Email</label>
                                <input type="email" class="form-input" id="cancel-email" name="email" placeholder="Email address" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <div class="form-field centered">
                                <label class="form-label" for="cancel-reservation-id">Reservation ID</label>
                                <input type="text" class="form-input" id="cancel-reservation-id" name="reservationId" placeholder="Enter your reservation ID" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <label class="form-label" for="cancellation-reason">Reason for cancellation</label>
                        <textarea class="form-textarea" id="cancellation-reason" name="cancellationReason" placeholder="Please tell us why you're cancelling your reservation" required></textarea>
                    </div>
                    
                    <div class="form-center">
                        <button type="submit" class="btn btn-secondary">Submit Cancellation</button>
                    </div>
                </form>
            </div>
            
            <div id="edit-tab" class="tab-content">
                <form id="editForm">
                    <div class="form-row">
                        <div class="form-group">
                            <div class="form-field centered">
                                <label class="form-label" for="edit-reservation-id">Reservation ID</label>
                                <input type="text" class="form-input" id="edit-reservation-id" name="reservationId" placeholder="Enter your reservation ID" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <label class="form-label" for="edit-name">Personal Information</label>
                        <div class="form-group">
                            <div class="form-field">
                                <input type="text" class="form-input" id="edit-first-name" name="firstName" placeholder="First name" required>
                            </div>
                            <div class="form-field">
                                <input type="text" class="form-input" id="edit-last-name" name="lastName" placeholder="Last name" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <div class="form-field">
                                <label class="form-label" for="edit-phone">Phone</label>
                                <input type="tel" class="form-input" id="edit-phone" name="phone" placeholder="Phone number" required>
                            </div>
                            <div class="form-field">
                                <label class="form-label" for="edit-email">Email</label>
                                <input type="email" class="form-input" id="edit-email" name="email" placeholder="Email address" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <label class="form-label">Reservation Details</label>
                        <div class="form-group">
                            <div class="form-field">
                                <label class="form-label" for="edit-date">Date</label>
                                <input type="date" class="form-input" id="edit-date" name="date" required>
                            </div>
                            <div class="form-field">
                                <label class="form-label" for="edit-time">Time</label>
                                <input type="time" class="form-input" id="edit-time" name="time" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <div class="form-field">
                                <label class="form-label" for="edit-guests">Number of Guests</label>
                                <select class="form-select" id="edit-guests" name="guests" required>
                                    <option value="">Select number of guests</option>
                                    <option value="1">1 person</option>
                                    <option value="2">2 people</option>
                                    <option value="3">3 people</option>
                                    <option value="4">4 people</option>
                                    <option value="5">5 people</option>
                                    <option value="6">6 people</option>
                                    <option value="7">7 people</option>
                                    <option value="8">8 people</option>
                                    <option value="9+">9+ people</option>
                                </select>
                            </div>
                            <div class="form-field">
                                <label class="form-label" for="edit-table">Table Type</label>
                                <select class="form-select" id="edit-table" name="tableType">
                                    <option value="">Select table type</option>
                                    <option value="Standard">Standard</option>
                                    <option value="Window">Window</option>
                                    <option value="Outdoor">Outdoor Patio</option>
                                    <option value="Private">Private Dining</option>
                                    <option value="Bar">Bar Area</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <label class="form-label" for="edit-requests">Special Requests</label>
                        <textarea class="form-textarea" id="edit-requests" name="specialRequests" placeholder="Any special requests or dietary requirements"></textarea>
                    </div>
                    
                    <div class="form-center">
                        <button type="submit" class="btn btn-primary">Update Reservation</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <div id="notification" class="notification">Reservation updated successfully!</div>
    
    <footer>
        <div class="copyright">
            <a href="index.html" class="footer-logo">Continental Bhansa</a>
            &copy; 2025 All rights reserved.
        </div>
    </footer>

    <!-- Include Font Awesome -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
    
    <script>
      // Tab Functionality
document.addEventListener('DOMContentLoaded', function() {
    const tabButtons = document.querySelectorAll('.tab-button');
    const tabContents = document.querySelectorAll('.tab-content');
    const notification = document.getElementById('notification');
    
    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const tabId = this.dataset.tab;
            
            // Update active button
            tabButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');
            
            // Update active content
            tabContents.forEach(content => content.classList.remove('active'));
            document.getElementById(`${tabId}-tab`).classList.add('active');
        });
    });
    
    // Find reservation functionality
    const findButton = document.getElementById('find-button');
    const searchMessage = document.getElementById('search-message');
    const reservationDetails = document.getElementById('reservation-details');
    
    findButton.addEventListener('click', function() {
        const reservationId = document.getElementById('find-reservation-id').value.trim();
        const email = document.getElementById('find-email').value.trim();
        
        if (!reservationId || !email) {
            showSearchMessage('Please enter both reservation ID and email address', 'error');
            reservationDetails.style.display = 'none';
            return;
        }
        
        // Here you would normally fetch the reservation data from a server
        // For demo purposes, we'll simulate a successful search
        
        // Simulate API call delay
        searchMessage.style.display = 'none';
        
        setTimeout(() => {
            // Mock reservation data - in a real app, this would come from your backend
            const reservationData = {
                firstName: 'Rajiv',
                lastName: 'Mehta',
                email: email,
                phone: '555-789-1234',
                reservationId: reservationId,
                date: '2025-04-25',
                time: '19:30',
                guests: 4,
                tableType: 'Private Dining',
                specialRequests: 'Birthday celebration, window seating if possible'
            };
            
            // Display the reservation details
            document.getElementById('detail-name').textContent = `${reservationData.firstName} ${reservationData.lastName}`;
            document.getElementById('detail-email').textContent = reservationData.email;
            document.getElementById('detail-phone').textContent = reservationData.phone;
            document.getElementById('detail-id').textContent = reservationData.reservationId;
            
            // Format date for display
            const dateObj = new Date(reservationData.date);
            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            document.getElementById('detail-date').textContent = dateObj.toLocaleDateString('en-US', options);
            
            // Format time for display
            const timeParts = reservationData.time.split(':');
            const hours = parseInt(timeParts[0]);
            const minutes = timeParts[1];
            const period = hours >= 12 ? 'PM' : 'AM';
            const formattedHours = hours % 12 || 12;
            document.getElementById('detail-time').textContent = `${formattedHours}:${minutes} ${period}`;
            
            document.getElementById('detail-guests').textContent = reservationData.guests;
            document.getElementById('detail-table').textContent = reservationData.tableType;
            document.getElementById('detail-requests').textContent = reservationData.specialRequests || 'None';
            
            // Show the details section
            reservationDetails.style.display = 'block';
            showSearchMessage('Reservation found!', 'success');
        }, 800);
    });
    
    // Go to edit tab with reservation details
    document.getElementById('go-to-edit').addEventListener('click', function() {
        // Get data from details view
        const reservationId = document.getElementById('detail-id').textContent;
        const fullName = document.getElementById('detail-name').textContent.split(' ');
        const firstName = fullName[0];
        const lastName = fullName.slice(1).join(' ');
        const email = document.getElementById('detail-email').textContent;
        const phone = document.getElementById('detail-phone').textContent;
        const tableType = document.getElementById('detail-table').textContent;
        
        // Switch to edit tab
        tabButtons.forEach(btn => btn.classList.remove('active'));
        document.querySelector('[data-tab="edit"]').classList.add('active');
        
        tabContents.forEach(content => content.classList.remove('active'));
        document.getElementById('edit-tab').classList.add('active');
        
        // Fill the edit form with reservation details
        document.getElementById('edit-reservation-id').value = reservationId;
        document.getElementById('edit-first-name').value = firstName;
        document.getElementById('edit-last-name').value = lastName;
        document.getElementById('edit-email').value = email;
        document.getElementById('edit-phone').value = phone;
        
        // Parse and set the date
        const dateText = document.getElementById('detail-date').textContent;
        const dateObj = new Date(dateText);
        const formattedDate = dateObj.toISOString().split('T')[0];
        document.getElementById('edit-date').value = formattedDate;
        
        // Parse and set the time
        const timeText = document.getElementById('detail-time').textContent;
        const timeMatch = timeText.match(/(\d+):(\d+)\s*(AM|PM)/i);
        if (timeMatch) {
            let hours = parseInt(timeMatch[1]);
            const minutes = timeMatch[2];
            const period = timeMatch[3].toUpperCase();
            
            if (period === 'PM' && hours < 12) hours += 12;
            if (period === 'AM' && hours === 12) hours = 0;
            
            const formattedTime = `${hours.toString().padStart(2, '0')}:${minutes}`;
            document.getElementById('edit-time').value = formattedTime;
        }
        
        // Set guests and table type
        const guests = document.getElementById('detail-guests').textContent;
        document.getElementById('edit-guests').value = guests;
        
        // Find the table type option and select it
        const tableSelect = document.getElementById('edit-table');
        for (let i = 0; i < tableSelect.options.length; i++) {
            if (tableSelect.options[i].text === tableType) {
                tableSelect.selectedIndex = i;
                break;
            }
        }
        
        const requests = document.getElementById('detail-requests').textContent;
        document.getElementById('edit-requests').value = requests === 'None' ? '' : requests;
    });
    
    // Go to cancel tab with reservation details
    document.getElementById('go-to-cancel').addEventListener('click', function() {
        // Get data from details view
        const reservationId = document.getElementById('detail-id').textContent;
        const fullName = document.getElementById('detail-name').textContent.split(' ');
        const firstName = fullName[0];
        const lastName = fullName.slice(1).join(' ');
        const email = document.getElementById('detail-email').textContent;
        const phone = document.getElementById('detail-phone').textContent;
        
        // Switch to cancel tab
        tabButtons.forEach(btn => btn.classList.remove('active'));
        document.querySelector('[data-tab="cancel"]').classList.add('active');
        
        tabContents.forEach(content => content.classList.remove('active'));
        document.getElementById('cancel-tab').classList.add('active');
        
        // Fill the cancel form with reservation details
        document.getElementById('cancel-reservation-id').value = reservationId;
        document.getElementById('cancel-first-name').value = firstName;
        document.getElementById('cancel-last-name').value = lastName;
        document.getElementById('cancel-email').value = email;
        document.getElementById('cancel-phone').value = phone;
    });
    
    // Handle form submissions
    document.getElementById('editForm').addEventListener('submit', function(e) {
        e.preventDefault();
        
        // In a real app, you would send this data to your server
        
        // Show success notification
        showNotification('Reservation updated successfully!');
        
        // Optionally, return to the find tab
        setTimeout(() => {
            tabButtons.forEach(btn => btn.classList.remove('active'));
            document.querySelector('[data-tab="find"]').classList.add('active');
            
            tabContents.forEach(content => content.classList.remove('active'));
            document.getElementById('find-tab').classList.add('active');
            
            // Reset search display
            reservationDetails.style.display = 'none';
            document.getElementById('find-reservation-id').value = '';
            document.getElementById('find-email').value = '';
            searchMessage.style.display = 'none';
        }, 1500);
    });
    
    document.getElementById('cancellationForm').addEventListener('submit', function(e) {
        e.preventDefault();
        
        // In a real app, you would send this data to your server
        
        // Show success notification
        showNotification('Reservation cancelled successfully!');
        
        // Return to the find tab and reset form
        setTimeout(() => {
            tabButtons.forEach(btn => btn.classList.remove('active'));
            document.querySelector('[data-tab="find"]').classList.add('active');
            
            tabContents.forEach(content => content.classList.remove('active'));
            document.getElementById('find-tab').classList.add('active');
            
            // Reset cancellation form
            this.reset();
            
            // Reset search display
            reservationDetails.style.display = 'none';
            document.getElementById('find-reservation-id').value = '';
            document.getElementById('find-email').value = '';
            searchMessage.style.display = 'none';
        }, 1500);
    });
    
    // Helper functions
    function showSearchMessage(message, type) {
        searchMessage.textContent = message;
        searchMessage.className = 'search-message';
        searchMessage.classList.add(type);
        searchMessage.style.display = 'block';
    }
    
    function showNotification(message) {
        notification.textContent = message;
        notification.classList.add('show');
        
        setTimeout(() => {
            notification.classList.remove('show');
        }, 3000);
    }
});
</script>

<%@ include file="Footer.jsp" %>
</body>
</html>
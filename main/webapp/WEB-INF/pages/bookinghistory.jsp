<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Continental Bhansa - Booking History</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookinghistory.css">
</head>
<body>
<%@ include file="navbar.jsp" %>

    

    <div class="dashboard-container">
        <div class="dashboard-content">
            <h1 class="page-title">Table Booking History</h1>
            
            <div class="booking-filters">
                <a href="#" class="filter-tab active">All bookings</a>
                <a href="#" class="filter-tab">Confirmed</a>
                <a href="#" class="filter-tab">Cancelled</a>
            </div>

            <div class="date-pickers">
                <div class="date-picker-wrapper">
                    <input type="date" class="date-picker-input" id="fromDate" placeholder="From Date">
                </div>
                <div class="date-picker-wrapper">
                    <input type="date" class="date-picker-input" id="toDate" placeholder="To Date">
                </div>
            </div>

            <table class="booking-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Table</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Guest 1</td>
                        <td>Table 1</td>
                        <td class="status-confirmed">Confirmed</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Guest 1</td>
                        <td>Table 2</td>
                        <td class="status-confirmed">Confirmed</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Guest 1</td>
                        <td>Table 5</td>
                        <td class="status-cancelled">Cancelled</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>Guest 1</td>
                        <td>Table 7</td>
                        <td class="status-cancelled">Cancelled</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>Guest 1</td>
                        <td>Table 3</td>
                        <td class="status-confirmed">Confirmed</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <section class="cta-section">
        <h2 class="cta-title">Ready to embark on a culinary journey?</h2>
        <p class="cta-subtitle">Create an account to easily manage your reservations and receive exclusive offers</p>
        <div class="cta-buttons">
            <a href="#" class="cta-btn cta-btn-primary">Create Account</a>
            <a href="#" class="cta-btn cta-btn-secondary">Subscribe to our newsletter</a>
        </div>
    </section>

    <%@ include file="Footer.jsp" %>
    <script>
        // Date picker functionality
        const fromDate = document.getElementById('fromDate');
        const toDate = document.getElementById('toDate');

        // Set minimum date to today
        const today = new Date().toISOString().split('T')[0];
        fromDate.min = today;
        toDate.min = today;

        // Update the min/max dates when one is selected
        fromDate.addEventListener('change', function() {
            if (this.value) {
                toDate.min = this.value;
            }
        });

        toDate.addEventListener('change', function() {
            if (this.value) {
                fromDate.max = this.value;
            }
        });

        // Tab filtering functionality
        document.querySelectorAll('.filter-tab').forEach(tab => {
            tab.addEventListener('click', function(e) {
                e.preventDefault();
                
                // Remove active class from all tabs
                document.querySelectorAll('.filter-tab').forEach(t => t.classList.remove('active'));
                
                // Add active class to clicked tab
                this.classList.add('active');
                
                // Filter functionality
                const filterType = this.textContent.trim().toLowerCase();
                const rows = document.querySelectorAll('.booking-table tbody tr');
                
                rows.forEach(row => {
                    const status = row.querySelector('td:last-child').textContent.trim().toLowerCase();
                    
                    if (filterType === 'all bookings') {
                        row.style.display = '';
                    } else if (filterType === status) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });
        });
    </script>
</body>
</html>
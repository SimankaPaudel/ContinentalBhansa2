<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> System Reports</title>
<link rel="stylesheet" href="report12.css">
 <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<h1>System Reports</h1>

<!-- Reservations Table -->
<div class="section">
    <h2>Reservations Report</h2>
    <table>
        <tr><th>Date</th><th>Total Reservations</th></tr>
        <c:forEach var="row" items="${reservationData}">
            <tr><td>${row.date}</td><td>${row.totalReservations}</td></tr>
        </c:forEach>
    </table>
</div>

<!-- Revenue Overview -->
<div class="section">
    <h2>Total Revenue</h2>
    <p class="summary-text">Rs. <span class="highlight">${revenue}</span></p>
</div>

<!-- User Activity -->
<div class="section">
    <h2>User Activity</h2>
    <table>
        <tr><th>User ID</th><th>Login Count</th></tr>
        <c:forEach var="user" items="${userActivity}">
            <tr><td>${user.user_id}</td><td>${user.login_count}</td></tr>
        </c:forEach>
    </table>
</div>

<!-- Review Summary -->
<div class="section">
    <h2>Review Summary</h2>
    <p class="summary-text">Total Reviews: <span class="highlight">${totalReviews}</span></p>
    <p class="summary-text">Average Rating: <span class="highlight">${averageRating}</span></p>
</div>

<!-- Insights -->
<div class="section">
    <h2>Insights</h2>
    <div class="insight-block">
        <h3>Peak Dining Hours</h3>
        <p>The most popular dining hours are between <span class="highlight">6 PM – 9 PM</span>.</p>
    </div>

    <div class="insight-block">
        <h3>Most Popular Dishes</h3>
        <ul>
            <li>Butter Chicken</li>
            <li>Spagetti Carbonara</li>
            <li>Manchurian</li>
        </ul>
    </div>
</div>

<!-- Bar Graph -->
<div class="section">
    <h2>Reservations Bar Chart</h2>
    <canvas id="reservationChart" height="120"></canvas>
</div>

<!-- Export Buttons -->
<div class="section">
    <h2>Export Options</h2>
    <button onclick="window.print()"> Print Report</button>
    <button onclick="exportPDF()"> Export as PDF</button>
    <button onclick="exportExcel()"> Export as Excel</button>
</div>

<div class="footer">
    © 2025 Continental Bhanchaa — All Rights Reserved
</div>

<!-- Chart.js & Export Script -->
<script>
    const ctx = document.getElementById('reservationChart');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            datasets: [{
                label: 'Reservations',
                data: [120, 150, 170, 160, 200, 300, 289], // Replace with dynamic values if needed
                backgroundColor: '#800000'
            }]
        },
        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'Weekly Reservation Summary'
                },
                legend: {
                    display: false
                }
            }
        }
    });

    // Placeholder export functions
    function exportPDF() {
        alert("PDF export functionality will be implemented with backend or JS library.");
    }

    function exportExcel() {
        alert("Excel export functionality will be implemented with backend or JS library.");
    }
</script>

</body>
</html>



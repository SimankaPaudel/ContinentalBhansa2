<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage Reservations</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage_reservation.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;600;700&display=swap" rel="stylesheet">


</head>
<body>


	<div class="layout">
		<!-- Sidebar -->
		<div class="sidebar">
  <h4 class="brand">Continental Bhansa</h4>

  <a href="${pageContext.request.contextPath}/admin_dashboard">
    <i class="fas fa-folder"></i> Dashboard
  </a>
  <a href="${pageContext.request.contextPath}/manage_reservation" class="active">
    <i class="fas fa-calendar-alt"></i> Reservations
  </a>
  <a href="${pageContext.request.contextPath}/manage_user">
    <i class="fas fa-users"></i> Users
  </a>
  <a href="${pageContext.request.contextPath}/admin/manage_menu">
    <i class="fas fa-bars"></i> Menu
  </a>

  <div class="logout-section">
    <a href="#"><i class="fas fa-sign-out-alt"></i> Logout</a>
  </div>
</div>

		<!-- Main Content -->
		<div class="main-content">
			<div class="header-row">
  			<div>
    			<h2 class="dashboard-header">Manage Reservations</h2>
    			<p>View and manage all restaurant reservations</p>
  			</div>
 			 <button class="new-reservation">+ New Reservation</button>
			</div>


			<!-- Search and Filter -->
			<div class="filter-row">
				<input type="text" class="search-input"
					placeholder="Search by name or contact..."> <select
					class="filter-select">
					<option>Filter by Status</option>
					<option>Confirmed</option>
					<option>Pending</option>
					<option>Cancelled</option>
				</select>
			</div>

			<!-- Reservations Table -->
			<div class="card">
				<div class="card-header bg-maroon text-white">
					<strong>Reservations</strong>
				</div>
				<table class="reservations-table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Date and Time</th>
							<th>Guests</th>
							<th>Contact</th>
							<th>Status</th>
							<th>Special Requests</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Rahul Sharma</td>
							<td>Tue, Apr 15, 2025<br>10:00 PM
							</td>
							<td>4 people</td>
							<td>+977 98765 43210</td>
							<td><span class="status-badge Confirmed">Confirmed</span></td>
							<td>Window seat, celebrating anniversary</td>
							<td><select class="action-select">
									<option>Status</option>
									<option>Confirm</option>
									<option>Cancel</option>
							</select>
								<button class="delete-btn">🗑</button></td>
						</tr>
						<tr>
							<td>Anmol KC</td>
							<td>Wed, Apr 16, 2025<br>9:00 PM
							</td>
							<td>2 people</td>
							<td>+977 9841 845521</td>
							<td><span class="status-badge Pending">Pending</span></td>
							<td>Prefer quiet corner</td>
							<td><select class="action-select">
									<option>Status</option>
									<option>Confirm</option>
									<option>Cancel</option>
							</select>
								<button class="delete-btn">🗑</button></td>
						</tr>
						<tr>
							<td>Ahmed Khan</td>
							<td>Wed, Apr 16, 2025<br>10:00 PM
							</td>
							<td>6 people</td>
							<td>+977 97085 623458</td>
							<td><span class="status-badge Confirmed">Confirmed</span></td>
							<td>Birthday celebration, need cake</td>
							<td><button class="btn btn-primary">Confirm</button>
							<button class="btn btn-primary">Cancel</button>
								<button class="delete-btn">🗑</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
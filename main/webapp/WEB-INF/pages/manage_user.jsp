<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage Users</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage_user.css">
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
  <a href="${pageContext.request.contextPath}/manage_reservation">
    <i class="fas fa-calendar-alt"></i> Reservations
  </a>
  <a href="${pageContext.request.contextPath}/manage_user" class="active">
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
					<h2 class="dashboard-header">Manage Users</h2>
					<p>View and manage all user accounts</p>
				</div>
				<button class="add-user-btn">
  					<i class="fas fa-user-plus"></i><a href="AddUserController">Add User</a> 
				</button>
			</div>

			<!-- Search and Filter -->
			<div class="filter-row">
				<input type="text" class="search-input"
					placeholder="Search by name, email, or phone...">
				<div class="filter-controls">
					<select>
						<option>Filter by Status</option>
						<option>Active</option>
						<option>Inactive</option>
						<option>Monitor</option>
					</select> <select>
						<option>Filter by Type</option>
						<option>Admin</option>
						<option>Customer</option>
					</select>
				</div>
			</div>

			<!-- Users Table -->
			<div class="card">
				<div class="card-header">Users</div>
				<table class="user-table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Contact Information</th>
							<th>Location</th>
							<th>Join Date</th>
							<th>Reservations</th>
							<th>Status</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Aayush Pradhan</td>
							<td>aayush123@gmail.com<br>+977 9876-456789
							</td>
							<td>Kathmandu, Nepal</td>
							<td>Oct 11, 2024</td>
							<td>12</td>
							<td><span class="status-badge Active">Active</span></td>
							<td><select class="action-select">
									<option>Status</option>
									<option>Active</option>
									<option>Inactive</option>
									<option>Monitor</option>
							</select>
								<button class="edit-btn">✏️</button>
								<button class="delete-btn">🗑</button></td>
						</tr>
						<tr>
							<td>Rajesh Singh</td>
							<td>rajesh.singh@xyz.com<br>+977 9841 589674
							</td>
							<td>Lalitpur, Nepal</td>
							<td>Dec 1, 2024</td>
							<td>3</td>
							<td><span class="status-badge Monitor">Monitor</span></td>
							<td><select class="action-select">
									<option>Status</option>
									<option>Active</option>
									<option>Inactive</option>
									<option>Monitor</option>
							</select>
								<button class="edit-btn">✏️</button>
								<button class="delete-btn">🗑</button></td>
						</tr>
						<tr>
							<td>Anita Sharma</td>
							<td>anita.sharma@email.com<br>+977 98510 69842
							</td>
							<td>Kalanki, Kathmandu, Nepal.</td>
							<td>Nov 25, 2024</td>
							<td>5</td>
							<td><span class="status-badge Active">Active</span></td>
							<td><select class="action-select">
									<option>Status</option>
									<option>Active</option>
									<option>Inactive</option>
									<option>Monitor</option>
							</select>
								<button class="edit-btn">✏️</button>
								<button class="delete-btn">🗑</button></td>
						</tr>
						<tr>
							<td>Rahul Sharma</td>
							<td>rahulsharma@abc.com<br>+977 98765 43210
							</td>
							<td>Lalitpur, Nepal</td>
							<td>Jun13, 2024</td>
							<td>3</td>
							<td><span class="status-badge Monitor">Monitor</span></td>
							<td><select class="action-select">
									<option>Status</option>
									<option>Active</option>
									<option>Inactive</option>
									<option>Monitor</option>
							</select>
								<button class="edit-btn">✏️</button>
								<button class="delete-btn">🗑</button></td>
						</tr>
						<tr>
							<td>Anmol KC</td>
							<td>kc.anmol@yahoo.coom<br>+977 9841 845521
							</td>
							<td>Lalitpur, Nepal</td>
							<td>April 21, 2020</td>
							<td>3</td>
							<td><span class="status-badge Monitor">Monitor</span></td>
							<td><select class="action-select">
									<option>Status</option>
									<option>Active</option>
									<option>Inactive</option>
									<option>Monitor</option>
							</select>
								<button class="edit-btn">✏️</button>
								<button class="delete-btn">🗑</button></td>
						</tr>
						<tr>
							<td>Ahmed Khan</td>
							<td>ahmenkhan123@hotmail.com<br>+977 97085 623458
							</td>
							<td>Lalitpur, Nepal</td>
							<td>Jan 1, 2025</td>
							<td>3</td>
							<td><span class="status-badge Monitor">Monitor</span></td>
							<td><select class="action-select">
									<option>Status</option>
									<option>Active</option>
									<option>Inactive</option>
									<option>Monitor</option>
							</select>
								<button class="edit-btn">✏️</button>
								<button class="delete-btn">🗑</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page import="java.util.List"%>
<%@ page import="com.continentalbhansa.model.User"%>
<%-- Replace with your actual User class package --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage Users</title>
<link rel="stylesheet" href="css/manage_user.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;600;700&display=swap"
	rel="stylesheet">
</head>
<body>

	<div class="layout">
		<!-- Sidebar -->
		<div class="sidebar">
			<h4 class="brand">Continental Bhansa</h4>
			<a href="${pageContext.request.contextPath}/"><i
				class="fas fa-folder"></i> Dashboard</a> <a
				href="${pageContext.request.contextPath}/manage_reservation"><i
				class="fas fa-calendar-alt"></i> Reservations</a> <a href="#"
				class="active"><i class="fas fa-users"></i> Users</a> <a
				href="${pageContext.request.contextPath}/manage_menu"><i
				class="fas fa-bars"></i> Menu</a>
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
				<a href="AddUserController" style="text-decoration: none;"
					class="add-user-btn"> <i class="fas fa-user-plus"></i> Add User
				</a>
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
						<%
						List<User> userList = (List<User>) request.getAttribute("userList");
						if (userList != null && !userList.isEmpty()) {
							for (User user : userList) {
						%>
						<tr>
							<td><%=user.getUserName()%></td>
							<td><%=user.getEmail()%><br><%=user.getPhoneNumber()%></td>
							<td><%=user.getAddress()%></td>
							<td>--</td>
							<td>--</td>
							<td><span class="status-badge Active">Active</span></td>
							<td>
								<form action="EditUserController" method="get"
									style="display: inline;">
									<input type="hidden" name="id" value="<%=user.getId()%>">
									<button type="submit" class="edit-btn">
										<i class="fas fa-edit"></i>
									</button>
								</form>
								<form action="DeleteUserController" method="post"
									style="display: inline;">
									<input type="hidden" name="username"
										value="<%=user.getUserName()%>">
									<button type="submit" class="delete-btn"
										onclick="return confirm('Are you sure you want to delete this user?');">🗑</button>
								</form></td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="7" style="text-align: center;">No users found.</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>

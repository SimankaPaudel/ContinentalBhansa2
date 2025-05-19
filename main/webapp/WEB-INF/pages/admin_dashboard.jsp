<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_dashboard.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;600;700&display=swap" rel="stylesheet">
  
  
</head>
<body>
	

 <div class="sidebar">
  <h4 class="brand">Continental Bhansa</h4>

  <a href="#" class="active">
    <i class="fas fa-folder"></i> Dashboard
  </a>
  <a href="${pageContext.request.contextPath}/manage_reservation">
    <i class="fas fa-calendar-alt"></i> Reservations
  </a>
  <a href="${pageContext.request.contextPath}/manage_user">
    <i class="fas fa-users"></i> Users
  </a>
  <a href="${pageContext.request.contextPath}/admin/manage_menu">
    <i class="fas fa-bars"></i> Menu
  </a>

  <div class="${pageContext.request.contextPath}/logout-section">
    <a href="#"><i class="fas fa-sign-out-alt"></i> Logout</a>
  </div>
</div>


  <!-- Main Content -->
  <div class="main-content">
    <div class="dashboard-title">
  <h1>Admin Dashboard</h1>
  <p>Welcome to Continental Bhansa Admin Portal</p>
</div>

    <!-- Top Info Cards -->
    <div class="card-container">
  <!-- Card 1 -->
  <div class="card">
    <div class="card-header-with-icon">
      <h4 class="card-title">Total Reservations</h4>
      <i class="fas fa-calendar-check card-icon"></i>
    </div>
    <div class="card-body">
      <p class="card-kpi">124</p>
    </div>
    <div class="card-footer">
      <p class="card-kpi-summary"><small>+12% from last month</small></p>
    </div>
  </div>

  <!-- Card 2 -->
  <div class="card">
    <div class="card-header-with-icon">
      <h4 class="card-title">Registered Users</h4>
      <i class="fas fa-users card-icon"></i>
    </div>
    <div class="card-body">
      <p class="card-kpi">573</p>
    </div>
    <div class="card-footer">
      <p class="card-kpi-summary"><small>+5% from last month</small></p>
    </div>
  </div>

  <!-- Card 3 -->
  <div class="card">
    <div class="card-header-with-icon">
      <h4 class="card-title">Reviews</h4>
      <i class="fas fa-star card-icon"></i>
    </div>
    <div class="card-body">
      <p class="card-kpi">89</p>
    </div>
    <div class="card-footer">
      <p class="card-kpi-summary"><small>+7% from last month</small></p>
    </div>
  </div>

  <!-- Card 4 -->
  <div class="card">
    <div class="card-header-with-icon">
      <h4 class="card-title">Menu Items</h4>
      <i class="fas fa-utensils card-icon"></i>
    </div>
    <div class="card-body">
      <p class="card-kpi">42</p>
    </div>
    <div class="card-footer">
      <p class="card-kpi-summary"><small>Updated 2 days ago</small></p>
    </div>
  </div>
</div>
    


    <!-- Sections -->
    <div class="section">
      <!-- Recent Reservations -->
      <div class="card">
  <h4>Recent Reservations</h4>
  <div class="reservation">
    Table for 2 – 4/15/2025, 7:30 PM 
    <span class="status confirmed">Confirmed</span>
  </div>
  <div class="reservation">
    Table for 4 – 4/16/2025, 7:30 PM 
    <span class="status pending">Pending</span>
  </div>
  <div class="reservation">
    Table for 6 – 4/17/2025, 7:30 PM 
    <span class="status confirmed">Confirmed</span>
  </div>
  <div class="reservation">
    Table for 8 – 4/18/2025, 7:30 PM 
    <span class="status cancelled">Cancelled</span>
  </div>
</div>


      <!-- Recent Reviews -->
      <div class="card">
  <h4>Recent Reviews</h4>

  <div class="review">
    <span><strong>Customer 1:</strong> Excellent food and ambient atmosphere.</span>
    <span class="stars">★★★★☆</span>
  </div>

  <div class="review">
    <span><strong>Customer 2:</strong> Service was top-notch, curry too spicy.</span>
    <span class="stars">★★★★★</span>
  </div>

  <div class="review">
    <span><strong>Customer 3:</strong> Loved the Butter Chicken!</span>
    <span class="stars">★★★★☆</span>
  </div>

  <div class="review">
    <span><strong>Customer 4:</strong> Great variety and presentation.</span>
    <span class="stars">★★★★★</span>
  </div>
</div>

    </div>
  </div>

</body>
</html>

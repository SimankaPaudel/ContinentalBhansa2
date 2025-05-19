<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Payment - Continental Bhansa</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Payment.css" />
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet" />
</head>
<body>

  <!-- ✅ Updated Navbar -->
  <%@ include file="navbar.jsp" %>

  <!-- Banner -->
  <section class="banner">
    <h1>Payment</h1>
    <p>Securely complete your order</p>
  </section>

  <!-- Payment Form -->
  <main class="payment-section">
    <div class="payment-card">
      <h2>Complete Your Payment</h2>
      <form>
        <label>Name on Card</label>
        <input type="text" placeholder="Full Name" required />

        <label>Card Number</label>
        <input type="text" placeholder="1234 5678 9012 3456" maxlength="19" required />

        <div class="row">
          <div>
            <label>Expiry Date</label>
            <input type="text" placeholder="MM/YY" maxlength="5" required />
          </div>
          <div>
            <label>CVV</label>
            <input type="text" placeholder="123" maxlength="3" required />
          </div>
        </div>

        <label>Billing Address</label>
        <input type="text" placeholder="Street, City, State" required />

        <button type="submit" class="pay-btn">Pay Now</button>
      </form>
    </div>
  </main>

  <!-- Footer -->
  <%@ include file="Footer.jsp" %>

  <div class="copyright">
    &copy; 2025 Continental Bhansa. All rights reserved.
  </div>

</body>
</html>

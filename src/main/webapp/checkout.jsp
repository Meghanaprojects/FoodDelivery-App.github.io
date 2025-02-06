<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("loggedUser ") == null) {
        response.sendRedirect("signin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout Page</title>
    <link rel="icon" href="Images/YummiQ.jpg">
    <link rel="stylesheet" href="checkout.css">
</head>
<body>
    <header>
        <h1>Checkout</h1>
        <nav>
            <ul class="nav-right">
                <li><a href="http://localhost:8080/Tap_foods/home">🏦 Home</a></li>
                <li><a href="cart.jsp">🛒 Cart</a></li>
            </ul>
        </nav>
    </header>

    <form action="checkout" method="post">
        <main class="main-content">
            <div class="container">
                <div class="billing-details">
                    <h2>Billing Details</h2>
                    <div class="form-group">
                        <label class="form-label">Full Name</label>
                        <input type="text" class="form-input" name="fullName" placeholder="Name" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Address</label>
                        <input type="text" class="form-input" name="address" placeholder="Address" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">City</label>
                        <input type="text" class="form-input" name="city" placeholder="City" required>
                    </div>
                    <div class="form-grid">
                        <div>
                            <label class="form-label">State</label>
                            <input type="text" class="form-input" name="state" placeholder="State" required>
                        </div>
                        <div>
                            <label class="form-label">Zip Code</label>
                            <input type="text" class="form-input" name="zipCode" placeholder="Zip Code" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Country</label>
                        <input type="text" class="form-input" name="country" placeholder="Country" required>
                    </div>
                </div>

                <div class="payment-card">
                    <h2>Payment Details</h2>
                    
                    <div class="payment-methods">
                        <label>
                            <input type="radio" name="paymentMethod" value="UPI" checked>
                            <span class="payment-method-icon">📱</span> UPI
                        </label>
                        <label>
                            <input type="radio" name="paymentMethod" value="Card">
                            <span class="payment-method-icon">💳</span> Card
                        </label>
                        <label>
                            <input type="radio" name="paymentMethod" value="COD">
                            <span class="payment-method-icon">🚚</span> COD
                        </label>
                        <label>
                            <input type="radio" name="paymentMethod" value="Wallet">
                            <span class="payment-method-icon">👝</span> Wallet
                        </label>
                        <label>
                            <input type="radio" name="paymentMethod" value="NetBanking">
                            <span class="payment-method-icon">🏦</span> NetBanking
                        </label>
                    </div>

                    <!-- <div class="form-group">
                        <label class="form-label">Select UPI App</label>
                        <select class="form-select" name="upiApp">
                            <option value="">Select UPI App</option>
                            <option value="gpay">Google Pay</option>
                            <option value="phonepe">PhonePe</option>
                            <option value="paytm">Paytm</option>
                            <option value="bhim">BHIM UPI</option>
                        </select>
                    </div> -->

                    <!-- Card Section (hidden by default) -->
                    <div class="form-group" style="display: none;">
                        <label class="form-label">Card Number</label>
                        <input type="text" class="form-input" name="cardNumber" placeholder="1234 5678 9012 3456">
                        
                        <div class="form-grid">
                            <div>
                                <label class="form-label">Expiry Date</label>
                                <input type="text" class="form-input" name="expiryDate" placeholder="MM/YY">
                            </div>
                            <div>
                                <label class="form-label">CVV</label>
                                <input type="text" class="form-input" name="cvv" placeholder="123">
                            </div>
                        </div>
                        
                        <label class="form-label">Card Holder Name</label>
                        <input type="text" class="form-input" name="cardHolderName" placeholder="Name on card">
                    </div>

                    <button type="submit" class="button">Place Order</button>
                </div>
            </div>
        </main>
    </form>
</body>
</html>
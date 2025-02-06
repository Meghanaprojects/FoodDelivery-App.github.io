<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Order" %>
<%@ page import="com.tap.model.OrderItem" %>
<%@ page import="com.tap.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    session = request.getSession(false);
    Order order = (Order) session.getAttribute("order"); // Retrieve the order from the session
    List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems"); // Retrieve order items from the session
    User user = (User ) session.getAttribute("loggedUser "); // Retrieve user details from the session

    if (order == null || orderItems == null || user == null) {
        response.sendRedirect("cart.jsp"); // Redirect if order, order items, or user is not found
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="icon" href="Images/YummiQ.jpg">
    <link rel="stylesheet" href="orderconfirmation.css">
</head>
<body>
    <div class="container">
        <div class="order-confirmation">
            <div class="header">
                <h1>Order Confirmation</h1>
                <p class="order-number">Order #<%= order.getOrderId() %></p>
            </div>
            
            <div class="success-message">
                <div class="checkmark">✓</div>
                <p>Thank you for your order!</p>
            </div>
            
           <!-- Adjusted Video ID -->
			<div class="animation">
			    <video id="orderAnimation" width="600" autoplay loop>
			        <source src="Images/delivery.mp4" type="video/mp4">
			        Your browser does not support the video tag.
			    </video>
			</div>

            <div class="order-details">
                <h2>Order Summary</h2>
                <%
                    int subtotal = 0; // Initialize subtotal
                    for (OrderItem item : orderItems) {
                        subtotal += item.getTotalPrice(); // Calculate subtotal
                %>
                <div class="item">
                    <span><%= item.getMenuId() %></span> <!-- Replace with actual item name if available -->
                    <span>₹<%= item.getTotalPrice() %></span>
                </div>
                <%
                    } // End of for loop
                %>
                <div class="subtotal">
                    <span>Subtotal</span>
                    <span>₹<%= subtotal %></span>
                </div>
                <div class="shipping">
                    <span>Shipping</span>
                    <span>₹39</span>
                </div>
                <div class="total">
                    <span>Total</span>
                    <span>₹<%= subtotal + 39 %></span> <!-- Add shipping cost to subtotal -->
                </div>
            </div>

            <div class="shipping-info">
                <h2>Shipping Details</h2>
                <p><%= user.getName() %></p> <!-- User's name -->
                <p><%= user.getAddress() %></p> <!-- User's address -->
            </div>
            

        </div>
    </div>
    <script>
    window.onload = function() {
        var video = document.getElementById('orderAnimation');
        video.style.display = 'block';

        video.addEventListener('canplay', function() {
            video.play();
        });

        video.addEventListener('error', function(e) {
            console.error('Error playing video:', e);
        });
    }
</script>


</body>
</html>
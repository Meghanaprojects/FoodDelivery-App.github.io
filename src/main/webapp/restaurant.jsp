<%@page import="com.tap.model.Restaurant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List,com.tap.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bangalore Restaurants</title>
    <link rel="icon" href="Images/YummiQ.jpg">
    <link rel="stylesheet" href="resturant.css">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar"> 
    
    
        <div class="nav-container">
            <div class="nav-brand">
                <span>YummiQ</span>
            </div>
            
       <!-- Search Bar -->
		<form class="search-bar" action="home" method="get">
		    <input type="text" name="query" placeholder="Find Restaurants" aria-label="Search Restaurants">
		    <button type="submit">Search</button>
		</form>

            <div class="nav-links">
                <a href="signin.jsp">➡️ Sign in</a>
                <!-- <a href="signup.jsp">➡️ Sign up</a> -->
                <a href="cart.jsp">🛒 Cart</a>
               <div class="profile-dropdown">
				    <input type="checkbox" id="profile-toggle" />
				    <label class="profile-icon" for="profile-toggle">👤 Profile</label>
				    <div class="dropdown-content">
				       <!--  <a href="order_history.jsp">Orders</a> -->
				        <a href="LogoutServlet">LogOut</a>
				    </div>
				</div>
            </div>
        </div>
    </nav>

    <!-- Header -->
    <header class="header">
        <h1>Restaurants in Bengaluru</h1>
    </header>
    
    <!-- Main Content -->
    <main class="main-content">
        <div class="restaurant-grid">
            <%
                List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("restaurants");
            	if (restaurants != null && !restaurants.isEmpty()) {
            	for (Restaurant restaurant : restaurants) {
            %>
            <div class="restaurant-card">
                <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>">
                    <img src="<%= restaurant.getImagePath() != null ? restaurant.getImagePath() : "default-image.jpg" %>"
                         alt="<%= restaurant.getName() %>" class="restaurant-image">
                </a>
                <div class="restaurant-info">
			    <h2><%= restaurant.getName() %></h2>
			    <div class="info-item">
			        <span>📍 <%= restaurant.getAddress() %></span>
			    </div>
			    <%-- 
			    <div class="info-item">
			        <span>📞 <%= restaurant.getPhone() %></span>
			    </div>
			    --%>
			    <div class="info-item rating-cuisine">
			        <span>🍽️ <%= restaurant.getCusineType() %></span>
			        <span>⭐ <%= restaurant.getRating() %></span>
			    </div>
			    <div class="info-item">
			        <span>🕒 <%= restaurant.getEta() %> mins</span>
			    </div>
			    <span class="status-badge <%= restaurant.getIsActive() ? "status-open" : "status-closed" %>">
			        <%= restaurant.getIsActive() ? "Open" : "Closed" %>
			    </span>
			</div>
            </div>
            <%
                }
            	} else {
                    %>
                        <p>No restaurants available.</p>
                    <%
                        }
            %>
        </div>
    </main>

    <!-- Footer -->
    <footer class="footer">
        <div class="footer-content">
            <div class="footer-section">
               
            <h3>About Us</h3>
            <p>At YummiQ, our mission is to bring the best culinary experiences right to your doorstep, making dining an enjoyable and seamless experience for everyone. Our vision is to be the leading food delivery service, known for quality, reliability, and exceptional customer service.</p>
            </div>
            <div class="footer-section">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="#">Home</a></li>
                    <!-- <li><a href="#">Contact</a></li> -->
                </ul>
            </div>
            <div class="footer-section">
                <h3>Contact Info</h3>
                <ul>
                    <li>Email: info@yummiq.com</li>
                    <li>Phone: +91 80 4242 4242</li>
                    <li>Address: Koramangala, Bengaluru</li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Follow Us</h3>
                <div class="social-links">
                    <a href="#">Twitter</a>
                    <a href="#">Facebook</a>
                    <a href="#">Instagram</a>
                    <a href="#">LinkedIn</a>
                	<a href="#">YouTube</a>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2024 YummiQ. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>
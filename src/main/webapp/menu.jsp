<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Menu" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Menu for Restaurant</title>
    <link rel="icon" href="Images/YummiQ.jpg">
    <link rel="stylesheet" href="menu.css">
</head>
<body>
    <header>
        <h1>Menu Items</h1>
        <nav>
            <ul class="nav-right">
                <li><a href="http://localhost:8080/Tap_foods/home">🏦 Home</a></li>
                <li><a href="cart.jsp">🛒 Cart</a></li>
            </ul>
        </nav>
    </header>
        
    <div class="menu-items">
        <% List<Menu> menuItems = (List<Menu>) request.getAttribute("menuItems");
            if (menuItems != null && !menuItems.isEmpty()) {
                for (Menu menuItem : menuItems) { %>
                    <div class="menu-item">
                        <img src="<%= menuItem.getImagePath() != null ? menuItem.getImagePath() : "default-image.jpg" %>"
                             alt="<%= menuItem.getItemName() %>" class="menu-image">
                        <div class="menu-item-content">
                            <h3><%= menuItem.getItemName() %></h3>
                            <p class="description"><%= menuItem.getDescription() %></p>
                            <div class="price-rating">
                                <p class="price">₹<%= menuItem.getPrice() %></p>
                                <div class="rating"><%= menuItem.getRatings() %>⭐</div>
                            </div>
                            <div class="availability <%= menuItem.getIsAvailable() ? "available" : "unavailable" %>">
                                <%= menuItem.getIsAvailable() ? "Available" : "Unavailable" %>
                            </div>
                            <form action="cart" method="post">
                                <input type="hidden" name="itemId" value="<%= menuItem.getMenuId() %>">
                                <input type="hidden" name="quantity" value="1" min="1">
                                <input type="hidden" name="restaurantId" value="<%= menuItem.getRestaurantId() %>">
                                <input type="hidden" name="action" value="add">
                                <button class="add-to-cart">Add to Cart</button>
                            </form>
                        </div>
                    </div>
                <% } // End of menuItems loop
            } else { %>
                <p>No menu items available for this restaurant.</p>
            <% } // End of menuItems check %>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.tap.utility.CartItem" %>
<%@ page import="com.tap.utility.Cart" %>
<%@ page import="com.tap.utility.CartHelper" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="icon" href="Images/YummiQ.jpg">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="cart.css">
</head>
<body>
   <header>
        <h1>Your Cart</h1>
        <nav>
            <ul class="nav-right">
                <li><a href="http://localhost:8080/Tap_foods/home">🏦 Home</a></li>
            </ul>
        </nav>
    </header>
    <div class="container">
        <form class="cart" action="" method="POST">
            <div class="cart-content">
                <div class="cart-items">
                    <%
                        Cart cart = (Cart) session.getAttribute("cart");
                        if (cart != null && !cart.getCart().isEmpty()) {
                            for (CartItem item : cart.getCart().values()) {
                    %>
                        <div class="cart-item">
                           <img src="<%= item.getImagePath() %>" alt="<%= item.getName() %>" class="item-image"> 
                            <div class="item-details">
                                <h3><%= item.getName() %></h3>
                                <p class="price">₹<%= item.getPrice() * item.getQuantity() %></p>
                                <div class="quantity-controls">
                                    <form action="cart" method="POST">
                                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                                        <button class="decrease">-</button>
                                    </form>

                                    <span class="quantity"><%= item.getQuantity() %></span>
                                     
                                    <form action="cart" method="POST">
                                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                                        <button class="increase">+</button>
                                    </form>
                                     
                                    <form action="cart" method="POST">
                                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                        <input type="hidden" name="action" value="remove">
                                        <button type="submit" name="remove" value="<%= item.getId() %>" class="remove-btn">
                                            <i class="fas fa-trash"></i> <!-- Font Awesome trash icon -->
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    <%
                            } // End of for loop
                        } else {
                    %>
                        <div class="empty-cart">
                            <img src="Images/emptycart.png" alt="Your cart is empty" class="empty-cart-image">
                            <p>Your cart is empty.</p>
                        </div>
                    <%
                        } // End of null check
                    %>
                </div>

                <div class="cart-summary">
                    <!-- Add More Items Button -->
                    <div class="add-more-items">
                        <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn add-more-items-btn">Browse Menu</a>
                    </div>
                    
                    <div class="summary-item">
                        <span>Subtotal:</span>
                        <span>₹<%= CartHelper.calculateSubtotal(cart) %></span>
                    </div>
                    <div class="summary-item">
                        <span>Shipping:</span>
                        <span>₹39</span>
                    </div>
                    <div class="summary-item total">
                        <span>Total:</span>
                        <span>₹<%= CartHelper.calculateTotal(cart) %></span>
                    </div>
                  
                    <%
                        if(session.getAttribute("cart") != null) {
                    %>
                    <form action="checkout.jsp" method="post">
                    <button type="submit" class="checkout-btn">Proceed to Checkout</button>
                    </form>
                    <%
                        }
                    %>
                </div>
            </div>
        </form>

    </div>
</body>
</html>

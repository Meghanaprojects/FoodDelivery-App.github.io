package com.tap.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.daoimplementation.OrderDAOImpl;
import com.tap.daoimplementation.OrderItemDAOImpl;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;
import com.tap.utility.Cart;
import com.tap.utility.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    
    private OrderDAO orderDAO;
    private OrderItemDAOImpl orderItemDAO;
    
    @Override
    public void init() throws ServletException {
        try {
            orderDAO = new OrderDAOImpl();
            orderItemDAO = new OrderItemDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User ) session.getAttribute("loggedUser ");
        String paymentMethod = req.getParameter("paymentMethod");

        // Debugging output
        System.out.println("Cart: " + cart);
        System.out.println(":User    " + user);
        System.out.println("Payment Method: " + paymentMethod);

        // Check if cart and user are valid
        if (cart != null && user != null && !cart.getCart().isEmpty()) {
            Order order = new Order();
            order.setUserId(user.getUserId());
            order.setRestaurantId((int) session.getAttribute("restaurantId"));
            order.setOrderDate(new Timestamp(System.currentTimeMillis())); // Set current timestamp
            order.setPaymentMode(paymentMethod);
            order.setStatus("Pending");

            int totalAmount = 0;
            for (CartItem item : cart.getCart().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }
            order.setTotalAmount(totalAmount);

            // Add order to the database
            int orderId = orderDAO.addOrder(order);
            System.out.println("Order ID: " + orderId); // Debugging output

            if (orderId > 0) { // Check if order was created successfully
                order.setOrderId(orderId); // Set the generated orderId in the order object
                List<OrderItem> orderItems = new ArrayList<>(); // Create a list to hold order items
                for (CartItem item : cart.getCart().values()) {
                    int id = item.getId();
                    int price = item.getPrice();
                    int quantity = item.getQuantity();
                    int totalPrice = price * quantity;

                    OrderItem orderItem = new OrderItem(orderId, id, quantity, totalPrice);
                    orderItemDAO.addOrderItem(orderItem);
                    orderItems.add(orderItem); // Add to the list
                    System.out.println("Added OrderItem: " + orderItem); // Debugging output
                }

                // Clear the cart and set the order and order items in the session
                session.removeAttribute("cart");
                session.setAttribute("order", order); // Set the order in the session
                session.setAttribute("orderItems", orderItems); // Set order items as a List in the session
                resp.sendRedirect("order_confirmation.jsp"); // Redirect to order confirmation
            } else {
                System.out.println("Failed to create order."); // Debugging output
                resp.sendRedirect("cart.jsp"); // Redirect to cart if order creation failed
            }
        } else {
            System.out.println("Cart is null or empty, or user is not logged in."); // Debugging output
            resp.sendRedirect("cart.jsp"); // Redirect to cart if conditions are not met
        }
    }
}
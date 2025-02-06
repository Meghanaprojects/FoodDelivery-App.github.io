package com.tap.servlets;

import java.io.IOException;

import com.tap.dao.MenuDAO;
import com.tap.daoimplementation.MenuDAOImpl;
import com.tap.model.Menu;
import com.tap.utility.Cart;
import com.tap.utility.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Get the current cart from the session
        HttpSession session = req.getSession();
        Cart cartItems = (Cart) session.getAttribute("cart");
        
        String restaurantIdParam = req.getParameter("restaurantId");
        if (restaurantIdParam != null) {
            int newRestaurantId = Integer.parseInt(restaurantIdParam);
            Integer currentRestaurantId = (Integer) session.getAttribute("restaurantId");

            if (cartItems == null || currentRestaurantId == null || newRestaurantId != currentRestaurantId) {
                cartItems = new Cart();
                session.setAttribute("cart", cartItems);
                session.setAttribute("restaurantId", newRestaurantId);
            }
        }
        
        String action = req.getParameter("action");
        
        try {
            if (action != null) {
                if (action.equals("add")) {
                    addItemToCart(req, cartItems);
                } else if (action.equals("update")) {
                    updateCartItem(req, cartItems);
                } else if (action.equals("remove")) {
                    deleteCartItem(req, cartItems);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Redirect back to the cart page
        resp.sendRedirect("cart.jsp");
    }
    
    private void addItemToCart(HttpServletRequest req, Cart cart) throws ClassNotFoundException {
        String itemIdParam = req.getParameter("itemId");
        String quantityParam = req.getParameter("quantity");

        if (itemIdParam != null && quantityParam != null) {
            int itemId = Integer.parseInt(itemIdParam);
            int quantity = Integer.parseInt(quantityParam);

            MenuDAO menuDAO = new MenuDAOImpl();
            Menu menuItem = menuDAO.getMenu(itemId);

            if (menuItem != null) {
                CartItem item = new CartItem(menuItem.getMenuId(), 
                                             menuItem.getItemName(),
                                             menuItem.getPrice(),
                                             quantity,
                                             menuItem.getImagePath());
                cart.addCartItem(item);
            }
        }
    }

    private void updateCartItem(HttpServletRequest req, Cart cart) throws ClassNotFoundException {
        String itemIdParam = req.getParameter("itemId");
        String quantityParam = req.getParameter("quantity");

        if (itemIdParam != null && quantityParam != null) {
            int itemId = Integer.parseInt(itemIdParam);
            int quantity = Integer.parseInt(quantityParam);

            System.out.println("Updating item ID: " + itemId + " with quantity: " + quantity); // Debug log

            cart.updateCartItem(itemId, quantity);
        } else {
            System.out.println("Item ID or quantity is null"); // Debug log
        }
    }

    
    private void deleteCartItem(HttpServletRequest req, Cart cart) throws ClassNotFoundException {
        String itemIdParam = req.getParameter("itemId");

        if (itemIdParam != null) {
            int itemId = Integer.parseInt(itemIdParam);
            cart.removeCartItem(itemId);
        }
    }
}

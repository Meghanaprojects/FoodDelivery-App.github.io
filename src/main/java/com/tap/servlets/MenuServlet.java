package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.daoimplementation.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the restaurant ID from the request parameter
        String restaurantIdParam = req.getParameter("restaurantId");
        
        System.out.println(restaurantIdParam);

        // Create an instance of the MenuDAO
        MenuDAO menuDAO = new MenuDAOImpl();

        int restaurantId = Integer.parseInt(restaurantIdParam);
        // Fetch the menu items for the specified restaurant
        List<Menu> menuItems = menuDAO.getMenusByRestaurantId(restaurantId);

        // Set the menu items as a request attribute
        req.setAttribute("menuItems", menuItems);

        // Forward the request to menu.jsp
        RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
        rd.forward(req, resp);
    }
}
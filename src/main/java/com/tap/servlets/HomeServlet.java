package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.daoimplementation.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");

        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        List<Restaurant> restaurants;

        if (query != null && !query.trim().isEmpty()) {
            restaurants = restaurantDAO.searchRestaurants(query);
        } else {
            restaurants = restaurantDAO.getAllRestaurants();
        }

        req.setAttribute("restaurants", restaurants);
        RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
        rd.forward(req, resp);
    }
}

package com.tap.servlets;

import java.io.IOException;

import com.tap.dao.UserDAO;
import com.tap.daoimplementation.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name"); // Retrieve the name parameter
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        User newUser  = new User();
        newUser .setName(name); // Set the name
        newUser .setUserName(username);
        newUser .setEmail(email);
        newUser .setPassword(password); // Consider hashing the password
        newUser .setPhone(phone);
        newUser .setAddress(address);
        newUser .setRole("Customer"); // Set default role

        UserDAO userDAO = new UserDAOImpl();
        userDAO.addUser(newUser);

        // Set a session attribute to indicate successful signup
        HttpSession session = req.getSession();
        session.setAttribute("signupSuccess", "Signup successful!!");

        // Redirect to the signin page after successful signup
        resp.sendRedirect("signin.jsp");
    }
}
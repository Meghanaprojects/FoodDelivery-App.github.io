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

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email != null && password != null) {
            UserDAO userDAO = new UserDAOImpl();
            User user = userDAO.authenticateUser (email, password);

            if (user != null) {
                // User exists, create a session and redirect to checkout
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser ", user);
                resp.sendRedirect("checkout.jsp");
            } else {
                // User does not exist, redirect to signup page
                HttpSession session = req.getSession();
                session.setAttribute("signupMessage", "New user? Please sign up!");
                resp.sendRedirect("signup.jsp");
            }
        } else {
            // Redirect to the signin page if email or password is missing
            resp.sendRedirect("signin.jsp");
        }
    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    session = request.getSession(false);
    String signupSuccess = (String) session.getAttribute("signupSuccess");
    if (signupSuccess != null) {
        session.removeAttribute("signupSuccess"); // Clear the attribute after displaying the message
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <link rel="icon" href="Images/YummiQ.jpg">
    <link rel="stylesheet" href="signup.css">
</head>
<body>
    <div class="container">
        <form class="signup-form" action="signin" method="post">
            <h1>Sign In</h1>

            <% if (signupSuccess != null) { %>
                <div class="alert alert-success">
                    <%= signupSuccess %>
                </div>
            <% } %>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>

            <button type="submit" class="btn">Sign In</button>

            <p class="login-link">
                Don't have an account? <a href="signup.jsp">Sign Up</a>
            </p>
        </form>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="icon" href="Images/YummiQ.jpg">
    <link rel="stylesheet" href="signup.css">
</head>
<body>
    <div class="container">
        <form class="signup-form" action="signup" method="post">
            <h1>Sign Up</h1>
            
            <div class="form-group">
                <label for="name">Full Name</label>
                <input 
                    type="text" 
                    id="name" 
                    name="name" 
                    required
                    minlength="3"
                    maxlength="50"
                >
            </div>

            <div class="form-group">
                <label for="username">Username</label>
                <input 
                    type="text" 
                    id="username" 
                    name="username" 
                    required
                    minlength="3"
                    maxlength="20"
                    pattern="[a-zA-Z0-9_-]+"
                >
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input 
                    type="email" 
                    id="email" 
                    name="email" 
                    required
                >
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input 
                    type="password" 
                    id="password" 
                    name="password" 
                    required
                    minlength="8"
                    pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                    title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 characters"
                >
            </div>

            <div class="form-group">
                <label for="confirm-password">Confirm Password</label>
                <input 
                    type="password" 
                    id="confirm-password" 
                    name="confirm-password" 
                    required
                >
            </div>

            <div class="form-group">
                <label for="phone">Phone</label>
                <input 
                    type="tel" 
                    id="phone" 
                    name="phone" 
                    required
                    pattern="[0-9]{10}"
                    title="Please enter a valid 10-digit phone number"
                >
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <textarea 
                    id="address" 
                    name="address" 
                    required
                    minlength="10"
                ></textarea>
            </div>

            <button type="submit" class="btn">Create Account</button>
            
            <p class="login-link">
                Already have an account? <a href="signin.jsp">Sign In</a>
            </p>
        </form>
    </div>
</body>
</html>
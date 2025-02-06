# 🍕 Yummiq - Food Delivery Web Application

Yummiq is a dynamic web-based food delivery platform designed to provide users with a seamless experience for exploring restaurants, browsing menus, placing orders. Built using Java (JSP/Servlets), MySQL, and Apache Tomcat, this application ensures a smooth and efficient ordering process.

# 🚀 Features

* Restaurant Listing: Display a curated list of restaurants for users to choose from.

* Menu Page: View detailed menu items with descriptions, prices, and images.

* User Authentication: Secure login and signup functionality to protect user data.

* Shopping Cart: Add, update, or remove items from the cart.

* Checkout Process: Streamlined checkout with order summary and payment options.

* Order Confirmation: Receive real-time order confirmation details.

# 🛠 Technologies Used

Frontend: HTML, CSS

Backend: Java (JSP, Servlets)

Database: MySQL

Server: Apache Tomcat


# 🔧 Installation and Setup

📌 Prerequisites

Java Development Kit (JDK)

Apache Tomcat Server

MySQL Database

Eclipse IDE (or any IDE supporting Dynamic Web Projects)


# 🛠 Steps to Set Up

1. Set Up the Database

Open MySQL and create a database:

CREATE DATABASE yummiq;

Import the provided yummiq.sql file to set up tables.



2. Configure the Project in Eclipse

Open Eclipse → File → Import → Dynamic Web Project → Select the Yummiq folder.

Configure Apache Tomcat as the server.

Right-click on the project → Run on Server → Select Tomcat.

3. Update Database Configuration

Open web.xml or DBConnection.java and update credentials:

private static final String URL = "jdbc:mysql://localhost:3306/yummiq";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";

4. Run the Application

Start Apache Tomcat in Eclipse.

# Open a browser and visit:

http://localhost:8080/Yummiq/home.

📷 Snapshots / Visuals
![Screenshot 2025-02-06 204815](https://github.com/user-attachments/assets/642fb717-2c86-432f-bbce-2f78e5525e6e)

![Screenshot 2025-02-06 205857](https://github.com/user-attachments/assets/5083edc1-6efd-4324-9d5b-bbf4953cdf01)

![Screenshot 2025-02-06 205914](https://github.com/user-attachments/assets/6d5ba7ce-f05b-4927-b194-e3e520eb1593)

![Screenshot 2025-02-06 205946](https://github.com/user-attachments/assets/46ad7181-8e21-4db2-9e02-86796155b2ed)

# 📩 Contact

For any queries, reach out at cmeghana319@gmail.com.

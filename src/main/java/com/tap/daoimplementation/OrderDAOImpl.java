package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO {

	String INSERT_ORDER_QUERY = "INSERT INTO `order` (`userId`, `restaurantId`, `orderDate`, `totalAmount`, `status`, `paymentMode`) VALUES (?, ?, ?, ?, ?, ?)";
    String GET_ORDER_QUERY = "SELECT * FROM `order` WHERE `orderId` = ?";
    String UPDATE_ORDER_QUERY = "UPDATE `order` SET `totalAmount` = ? WHERE `orderId` = ?";
    String DELETE_ORDER_QUERY = "DELETE FROM `order` WHERE `orderId` = ?";
    String GET_ALL_ORDER_QUERY = "SELECT * FROM `order`";

    @Override
    public int addOrder(Order order) {
        int orderId = 0;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setInt(1, order.getUserId());
            prepareStatement.setInt(2, order.getRestaurantId());
            prepareStatement.setTimestamp(3, order.getOrderDate()); // Set the orderDate
            prepareStatement.setInt(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getPaymentMode());

            int res = prepareStatement.executeUpdate();
            System.out.println("Executed query: " + prepareStatement);
            if (res > 0) {
                try (ResultSet keys = prepareStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        orderId = keys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDER_QUERY)) {

            prepareStatement.setInt(1, orderId);

            try (ResultSet res = prepareStatement.executeQuery()) {
                if (res.next()) {
                    order = extractOrder(res);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_ORDER_QUERY)) {

            prepareStatement.setInt(1, order.getTotalAmount());
            prepareStatement.setInt(2, order.getOrderId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDER_QUERY)) {

            prepareStatement.setInt(1, orderId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> ordersList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(GET_ALL_ORDER_QUERY)) {

            while (res.next()) {
                Order order = extractOrder(res);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }
    
    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> ordersList = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE userId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = extractOrder(resultSet);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersList;
    }


    private Order extractOrder(ResultSet res) throws SQLException {
        int orderId = res.getInt("orderId");
        int userId = res.getInt("userId");
        int restaurantId = res.getInt("restaurantId");
        Timestamp orderDate = res.getTimestamp("orderDate"); // Extract orderDate
        int totalAmount = res.getInt("totalAmount");
        String status = res.getString("status");
        String paymentMode = res.getString("paymentMode");

        return new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode);
    }

}

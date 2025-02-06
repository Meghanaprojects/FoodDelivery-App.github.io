package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImpl implements UserDAO {

	String INSERT_USER_QUERY = "INSERT INTO `user` (`name`, `username`, `password`, `email`, `phone`, `address`, `role`, `createdDate`, `lastLoginDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String GET_USER_QUERY = "SELECT * FROM `user` WHERE `userId` = ?";
    String UPDATE_USER_QUERY = "UPDATE `user` SET `name` = ?, `password` = ?, `phone` = ?, `address` = ?, `role` = ? WHERE `userId` = ?";
    String DELETE_USER_QUERY = "DELETE FROM `user` WHERE `userId` = ?";
    String GET_ALL_USER_QUERY = "SELECT * FROM `user`";
    String AUTHENTICATE_USER_QUERY = "SELECT * FROM `user` WHERE `email` = ? AND `password` = ?";

    @Override
    public void addUser(User user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USER_QUERY)) {

            System.out.println("Name: " + user.getName());
            System.out.println("Username: " + user.getUserName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhone());
            System.out.println("Address: " + user.getAddress());
            System.out.println("Role: " + user.getRole());

            prepareStatement.setString(1, user.getName());
            prepareStatement.setString(2, user.getUserName());
            prepareStatement.setString(3, user.getPassword());
            prepareStatement.setString(4, user.getEmail());
            prepareStatement.setString(5, user.getPhone());
            prepareStatement.setString(6, user.getAddress());
            prepareStatement.setString(7, user.getRole());
            prepareStatement.setTimestamp(8, user.getCreatedDate()); // Set the createdDate
            prepareStatement.setTimestamp(9, user.getLastLoginDate()); // Set the lastLoginDate

            int res = prepareStatement.executeUpdate();
            if (res > 0) {
                System.out.println("User added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding user to the database", e);
        }
    }
	
	@Override
	public User getUser(int userId) {	
		
		User user = null;
				
		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_QUERY))
		{
			
			prepareStatement.setInt(1,userId);
			
			try (ResultSet resultSet = prepareStatement.executeQuery())
			{ 
				if (resultSet.next()) 
			
				{ 
					user = extractUser(resultSet);
				} 
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		
		try
		{
		    connection = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(UPDATE_USER_QUERY);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getPhone());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setString(5, user.getRole());
			prepareStatement.setInt(6, user.getUserId());
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
	
		try
		{
			Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_USER_QUERY);
			prepareStatement.setInt(1,userId);
			
			prepareStatement.executeUpdate();			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		
		ArrayList<User> usersList = new ArrayList<User>();
		
		try {
			Connection connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			
			ResultSet res = statement.executeQuery(GET_ALL_USER_QUERY);
			
			while(res.next())
			{
				User user = extractUser(res);
				usersList.add(user);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return usersList;
	}
	
	@Override
    public User authenticateUser(String email, String password) {
        User user = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(AUTHENTICATE_USER_QUERY)) {

            prepareStatement.setString(1, email);
            prepareStatement.setString(2, password);

            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUser(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
	}
	
	User extractUser(ResultSet res) throws SQLException {
	    int userId = res.getInt("userId");
	    String name = res.getString("name");
	    String username = res.getString("username");
	    String password = res.getString("password");
	    String email = res.getString("email");
	    String phone = res.getString("phone");
	    String address = res.getString("address");
	    String role = res.getString("role");
	    Timestamp createdDate = res.getTimestamp("createdDate"); // Extract createdDate
	    Timestamp lastLoginDate = res.getTimestamp("lastLoginDate"); // Extract lastLoginDate

	    return new User(userId, name, username, password, email, phone, address, role, createdDate, lastLoginDate);
	}


}

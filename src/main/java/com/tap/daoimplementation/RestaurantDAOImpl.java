package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

	String INSERT_RESTAURANT_QUERY = "Insert into `restaurant` (`name`,`address`,`phone`,`rating`,`isActive`,`cusineType`,`eta`,`adminUserId`,`imagePath`) values (?,?,?,?,?,?,?,?,?) ";
	String GET_RESTAURANT_QUERY = "Select * from `restaurant` where `restaurantId` = ?";
	String UPDATE_RESTAURANT_QUERY = "Update `restaurant` set `name` = ? `address` = ? `phone` = ? `rating` = ? `isActive`=? `cusineType` = ? `eta` = ? `imagePath` = ? where `restaurantId` = ?";
	String DELETE_RESTAURANT_QUERY = "Delete from `restaurant` where `restaurantId` = ? ";
	String GET_ALL_RESTAURANT_QUERY = "Select * from `restaurant`";
	String SEARCH_RESTAURANTS_QUERY = "SELECT * FROM `restaurant` WHERE `name` LIKE ? OR `address` LIKE ? OR `cuisineType` LIKE ?";

	
	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_RESTAURANT_QUERY))
		{
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhone());
			prepareStatement.setDouble(4, restaurant.getRating());
			prepareStatement.setBoolean(5, restaurant.getIsActive());
			prepareStatement.setString(6, restaurant.getCusineType());
			prepareStatement.setTime(7, restaurant.getEta());
			prepareStatement.setString(8, restaurant.getImagePath());
			
			int res = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		
		Restaurant restaurant = null;
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_RESTAURANT_QUERY))
		{
			prepareStatement.setInt(1, restaurantId);
			
			ResultSet res = prepareStatement.executeQuery();
			
			restaurant = extractRestaurant(res);
			
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		
		Connection connection = null;
		
		PreparedStatement prepareStatement = null;
		
		try
		{
			connection = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(UPDATE_RESTAURANT_QUERY);
			
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhone());
			prepareStatement.setDouble(4, restaurant.getRating());
			prepareStatement.setBoolean(5, restaurant.getIsActive());
			prepareStatement.setString(6, restaurant.getCusineType());
			prepareStatement.setTime(7, restaurant.getEta());
			prepareStatement.setString(8, restaurant.getImagePath());
			prepareStatement.setInt(9, restaurant.getRestaurantId());
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		
		try {
			
			Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_RESTAURANT_QUERY);
			prepareStatement.setInt(1, restaurantId);
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
	
		ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();
		
		try
		{
			Connection connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			
			ResultSet res = statement.executeQuery(GET_ALL_RESTAURANT_QUERY);
			
			while(res.next())
			{
				Restaurant restaurant = extractRestaurant(res);
				restaurantsList.add(restaurant);
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return restaurantsList;
	}
	
	@Override
	public List<Restaurant> searchRestaurants(String query) {
	    List<Restaurant> filteredRestaurants = new ArrayList<>();

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_RESTAURANTS_QUERY)) {

	        String searchQuery = "%" + query + "%";
	        preparedStatement.setString(1, searchQuery);
	        preparedStatement.setString(2, searchQuery);
	        preparedStatement.setString(3, searchQuery);

	        System.out.println("Executing query: " + preparedStatement);

	        ResultSet res = preparedStatement.executeQuery();

	        while (res.next()) {
	            Restaurant restaurant = extractRestaurant(res);
	            filteredRestaurants.add(restaurant);
	        }

	        System.out.println("Number of restaurants found: " + filteredRestaurants.size());

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return filteredRestaurants;
	}
	
	Restaurant extractRestaurant(ResultSet res) throws SQLException
	{
		int restaurantId = res.getInt("restaurantId");
		String name = res.getString("name");
		String address = res.getString("address");
		String phone = res.getString("phone");
		Double rating = res.getDouble("rating");
		String cuisineType = res.getString("cuisineType");
		Boolean isActive = res.getBoolean("isActive");
		Time eta = res.getTime("eta");
		int adminUserId = res.getInt("adminUserId");
		String imagePath = res.getString("imagePath");
		
		Restaurant restaurant = new Restaurant(restaurantId, name, address, phone, rating, cuisineType, isActive, eta, adminUserId, imagePath);
		
		return restaurant;
		
	}
}
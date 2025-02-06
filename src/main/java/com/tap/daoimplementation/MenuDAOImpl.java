package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO{
	
	String INSERT_MENU_QUERY = "Insert into `menu` (`restaurantId`,`itemName`,`description`,`price`,`ratings`,`isAvailable`,`imagePath`) values (?,?,?,?,?,?,?) ";
	String GET_MENU_QUERY = "Select * from `menu` where `menuId` = ?";
	String UPDATE_MENU_QUERY = "UPDATE `menu` SET `itemName` = ?, `description` = ?, `price` = ?, `ratings` = ?, `isAvailable` = ?, `imagePath` = ? WHERE `menuId` = ?";
	String DELETE_MENU_QUERY = "Delete from `menu` where `menuId` = ? ";
	String GET_ALL_MENU_QUERY = "Select * from `menu`";
	String GET_MENUS_BY_RESTAURANT_ID_QUERY = "Select * from `menu` where `restaurantId` = ?";


	@Override
	public void addMenu(Menu menu) {
	
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_MENU_QUERY))
		{
			prepareStatement.setInt(1, menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
			prepareStatement.setString(3, menu.getDescription());
			prepareStatement.setInt(4, menu.getPrice());
			prepareStatement.setDouble(5, menu.getRatings());
			prepareStatement.setBoolean(6, menu.getIsAvailable());
			prepareStatement.setString(7, menu.getImagePath());
			
			int res = prepareStatement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Menu getMenu(int menuId) {
	    Menu menu = null;
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement prepareStatement = connection.prepareStatement(GET_MENU_QUERY)) {
	        
	        prepareStatement.setInt(1, menuId);
	        
	        ResultSet res = prepareStatement.executeQuery();
	        
	        if (res.next()) { // Move the cursor to the first row
	            menu = extractMenu(res);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }    
	    return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		
		try
		{
			connection = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(UPDATE_MENU_QUERY);
			
			prepareStatement.setString(1, menu.getItemName());
			prepareStatement.setString(2, menu.getDescription());
			prepareStatement.setInt(3, menu.getPrice());
			prepareStatement.setDouble(4, menu.getRatings());
			prepareStatement.setBoolean(5, menu.getIsAvailable());
			prepareStatement.setString(6, menu.getImagePath());
			prepareStatement.setInt(7, menu.getMenuId());
			
			prepareStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteMenu(int menuId) {
	
		try
		{
			Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_MENU_QUERY);
			
			prepareStatement.setInt(1, menuId);
			
			prepareStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenus() {
		
		ArrayList<Menu> menusList = new ArrayList<Menu>();
		
		try {
			Connection connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			
			ResultSet res = statement.executeQuery(GET_ALL_MENU_QUERY);
			
			while(res.next())
			{
				Menu menu = extractMenu(res);
				menusList.add(menu);
			}
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		}	
		return menusList;
	}

	@Override
	public List<Menu> getMenusByRestaurantId(int restaurantId) {
	    List<Menu> menusList = new ArrayList<>();
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement prepareStatement = connection.prepareStatement(GET_MENUS_BY_RESTAURANT_ID_QUERY)) {
	        prepareStatement.setInt(1, restaurantId);
	        ResultSet res = prepareStatement.executeQuery();
	        
	        while (res.next()) {
	            Menu menu = extractMenu(res);
	            menusList.add(menu);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return menusList;
	}
	
	Menu extractMenu(ResultSet res) throws SQLException {
		
		int menuId = res.getInt("menuId");
		int resaurantId = res.getInt("restaurantId");
		String itemName = res.getString("itemName");
		String description = res.getString("description");
		int price = res.getInt("price");
		Double ratings = res.getDouble("ratings");
		Boolean isAvailable = res.getBoolean("isAvailable");
		String imagePath = res.getString("imagePath");
		
		Menu menu = new Menu(menuId, resaurantId, itemName, description, price, ratings, isAvailable, imagePath);
		
		return menu;
	}

}

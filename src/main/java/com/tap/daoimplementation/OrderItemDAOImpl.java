package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	String INSERT_ORDERITEM_QUERY = "Insert into `orderItem` (`orderId`,`menuId`,`quantity`,`totalPrice`) values (?,?,?,?) ";
	String GET_ORDERITEM_QUERY = "Select * from `orderItem` where `orderItemId` = ?";
	String UPDATE_ORDERITEM_QUERY = "Update `orderItem` set `quantity` = ? `totalPrice` = ? where `orderItemId` = ?";
	String DELETE_ORDERITEM_QUERY = "Delete from `orderItem` where `orderItemId` = ? ";
	String GET_ALL_ORDERITEM_QUERY = "Select * from `orderItem`";
	
	@Override
	public void addOrderItem(OrderItem orderItem) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDERITEM_QUERY))
		{
		
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setInt(4, orderItem.getTotalPrice());
			
			int res = prepareStatement.executeUpdate();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		
		OrderItem orderItem = null;
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDERITEM_QUERY))
				{
				
					prepareStatement.setInt(1, orderItemId);
					
					ResultSet res = prepareStatement.executeQuery();
					
					orderItem = extractOrderItem(res);
						
			    } catch (SQLException e) {
					
					e.printStackTrace();
				}
		return orderItem;
	}

	@Override
	public void updateOrder(OrderItem orderItem) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		
		try
		{
			connection = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(UPDATE_ORDERITEM_QUERY);
					
			prepareStatement.setInt(1, orderItem.getQuantity());
			prepareStatement.setInt(2, orderItem.getTotalPrice());
			
			prepareStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}		
		
	}

	@Override
	public void deleteOrder(int orderItemId) {
	
		try
		{
			Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDERITEM_QUERY);
			
			prepareStatement.setInt(1, orderItemId);
			
			prepareStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		

		ArrayList<OrderItem> orderItemsList = new ArrayList<OrderItem>();
		
		try {
			Connection connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			
			ResultSet res = statement.executeQuery(GET_ALL_ORDERITEM_QUERY);
			
			while(res.next())
			{
				OrderItem orderItem = extractOrderItem(res);
				orderItemsList.add(orderItem);
			}
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		}
		return orderItemsList;
	}

	OrderItem extractOrderItem(ResultSet res) throws SQLException {
		
		int orderItemId = res.getInt("orderItemId");
		int orderId = res.getInt("orderId");
		int menuId = res.getInt("menuId");
		int quantity = res.getInt("quantity");
		int totalPrice = res.getInt("totalPrice");
		
		OrderItem orderItem = new OrderItem(orderId, menuId, quantity, totalPrice);
		
		return orderItem;
	}

}

package com.tap.dao;

import java.util.List;

import com.tap.model.OrderItem;

public interface OrderItemDAO {
	
	void addOrderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrder(OrderItem orderItem);
	void deleteOrder(int orderItemId);
	List<OrderItem> getAllOrderItems();

}
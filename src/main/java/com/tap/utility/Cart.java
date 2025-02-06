package com.tap.utility;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	    private Map<Integer, CartItem> cart;

	    // Constructor
	    public Cart() {
	        this.cart = new HashMap<>();
	    }
	    
	    public Map<Integer, CartItem> getCart() {
	        return cart;
	    }

	    // Add an item to the cart
	    public void addCartItem(CartItem ci) {
	        if (cart.containsKey(ci.getId())) {
	            CartItem existingItem = cart.get(ci.getId());
	            existingItem.setQuantity(existingItem.getQuantity() + ci.getQuantity());
	        } else {
	            cart.put(ci.getId(), ci);
	        }
	    }

	    // Update an item's quantity in the cart
	    public void updateCartItem(int id, int quantity) {
	        if (cart.containsKey(id)) {
	            if (quantity <= 0) {
	                cart.remove(id);
	            } else {
	                CartItem item = cart.get(id);
	                item.setQuantity(quantity);
	            }
	        }
	    }

	    // Remove an item from the cart
	    public void removeCartItem(int id) {
	        cart.remove(id);
	    }
	    
	    public void clear() {
			cart.clear();
		}
}
package com.tap.utility;

import java.util.Map;

public class CartHelper {

    public static double calculateSubtotal(Cart cart) {
        double subtotal = 0.0;
        if (cart != null) {
            for (CartItem item : cart.getCart().values()) {
                subtotal += item.getPrice() * item.getQuantity();
            }
        }
        return subtotal;
    }

    public static double calculateTotal(Cart cart) {
        double subtotal = calculateSubtotal(cart);
        double shipping = 39.0; // Fixed shipping cost
        return subtotal + shipping;
    }
}

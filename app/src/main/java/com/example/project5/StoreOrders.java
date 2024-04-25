package com.example.project5;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents a collection of orders for a store.
 *
 * @author Sharukh Khan, Hamad Naveed
 */
public class StoreOrders {
    private final ArrayList<Order> orders;
    private int lastOrderNumber = 0;

    public Collection<Order> getOrders() {
        return orders;
    }


    /**
     * Constructor for StoreOrders class
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     Removes an order with the specified order number from the store's list of orders.
     @param orderNumber the order number of the order to be removed
     */
    public void removeOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getNumOrder() == orderNumber) {
                orders.remove(order);
                return;
            }
        }
    }

    /**
     Adds an Order to the list of orders.
     If the added object is not an instance of the Order class, it returns false.
     If the added object is an instance of the Order class, it increments the last order number and sets it to the new order.
     Finally, it adds the order to the list of orders.
     @param obj the object to be added to the list of orders
     @return true if the object added is an instance of the Order class, false otherwise
     */
    public boolean add(Object obj) {
        if (obj instanceof Order order) {
            lastOrderNumber++;
            order.setNumOrder(lastOrderNumber);
            return orders.add(order);
        }
        return false;
    }

}
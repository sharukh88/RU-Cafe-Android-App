package com.example.project5;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of order (coffee and/or donut) instances created by order number
 * @author Sharukh Khan, Hamad Naveed
 */
public class Order {
    private int numOrder;
    public static final double TAX = 0.06625;
    private final ArrayList<MenuItem> list;

    /**
     * Order class constructor
     */
    public Order() {
        this.list = new ArrayList<>();
    }

    /**
     * Order class constructor
     * @param order
     */
    public Order(Order order) {
        this.list = new ArrayList<>(order.list);
        this.numOrder = order.numOrder;
    }

    /**
     * set the order number
     * @param number
     */
    public void setNumOrder(int number) {
        this.numOrder = number;
    }

    /**
     * get the order number
     * @return
     */
    public int getNumOrder() {
        return numOrder;
    }

    /**
     * get the subtotal of the order
     * @return subtotal
     */
    public double getSubTotal() {
        double subTotal = 0.0;
        for (MenuItem item : list) {
            subTotal += item.getCost();
        }
        return subTotal;
    }

    /**
     * get the tax of the order
     * @return sub total with tax
     */
    public double getTax() {
        return getSubTotal() * TAX;
    }

    /**
     * remove from order
     * @param mItem
     */
    public void remove(Object mItem) {
        if (mItem instanceof MenuItem item) {
            list.remove(item);
        }
    }

    /**
     * add to order
     * @param mItem
     * @return
     */
    public boolean add(Object mItem) {
        if (mItem instanceof MenuItem item) {
            list.add(item);
            return true;
        }
        return false;
    }

    /**
     * get the items in menu
     * @return
     */
    public List<MenuItem> getItems() {
        return list;
    }

    /**
     * get the total price
     * @return
     */
    public double getTotal() {
        return getSubTotal() * (1 + TAX);
    }

    /**
     * convert order to toString
     * @return
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("🛍️ Order #").append(this.getNumOrder()).append("\n");

        for (MenuItem item : list) {
            builder.append("     ➤ ").append(item).append("\n");
        }

        builder.append("\n");
        builder.append("💰 Subtotal: $").append(MainActivity.formatPrice(this.getSubTotal())).append("\n");
        builder.append("🧾 Tax: $").append(MainActivity.formatPrice(this.getTax())).append("\n");
        builder.append("🎉 Order Total: $").append(MainActivity.formatPrice(this.getTotal()));

        return builder.toString();
    }
}
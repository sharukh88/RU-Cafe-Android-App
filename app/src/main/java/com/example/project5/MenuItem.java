package com.example.project5;

import android.annotation.SuppressLint;

/**
 * Abstract class that represents a menu item and defines its characteristics.
 * Characteristics include the unit price, quantity, and cost of the item.
 * This class serves as a base class for other menu item classes to inherit from.
 *
 * The implementation of the abstract methods getUnitAmount() and getAmount()
 * is left to the concrete subclasses, while the methods getCostText() and
 * getCost() are implemented in this abstract class.
 *
 * @author Sharukh Khan, Hamad Naveed
 */
public abstract class MenuItem {

    /**
     * Returns the unit price of the menu item.
     * @return the unit price of the menu item
     */
    public abstract double getUnitAmount();

    /**
     * Returns the quantity of the menu item.
     * @return the quantity of the menu item
     */
    public abstract int getAmount();

    /**
     * Returns the total cost of the menu item based on its unit price and quantity.
     * @return the total cost of the menu item
     */
    public double getCost() {
        return getUnitAmount() * getAmount();
    }

    /**
     * Returns the cost of the menu item as a formatted string with two decimal places.
     * @return a formatted string representing the cost of the menu item
     */
    @SuppressLint("DefaultLocale")
    public String getCostText() {
        return String.format("%1$.2f", getCost());
    }
}
package com.example.project5;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A type of MenuItem that represents a donut.
 * A donut includes a type, flavor, and quantity.
 * * @author Sharukh Khan, Hamad Naveed
 */
public class Donut extends MenuItem {
    private int quantity;
    private final int image;
    private final String flavor;

    /**
     * set arraylist of yeast donut flavors
     */
    private static final ArrayList<String> YEAST_FLAVORS = new ArrayList<>() {{
        add("Creamy Glazed (Yeast)");
        add("Jelly Filled (Yeast)");
        add("Chocolate Frosted (Yeast)");
        add("Strawberry Frosted (Yeast)");
        add("Original Sugar (Yeast)");
        add("Lemon Filled (Yeast)");
    }};

    /**
     * set arraylist of cake donut flavors
     */
    private static final ArrayList<String> CAKE_FLAVORS = new ArrayList<>() {{
        add("Cinnamon Sugar (Cake)");
        add("Blueberry Blues (Cake)");
        add("Old Fashion (Cake)");
    }};

    /**
     * set arraylist of donut holes flavors
     */
    private static final ArrayList<String> HOLE_FLAVORS = new ArrayList<>() {{
        add("Creamy Glazed (Holes)");
        add("Jelly Filled (Holes)");
        add("Cinnamon Sugar (Holes)");
    }};

    /**
     * add prices into arraylist od donuts for each type.
     */
    private static final ArrayList<Double> PRICES = new ArrayList<>() {{
        add(1.59);
        add(1.79);
        add(0.39);
    }};

    /**
     * donut consturctor
     * @param other
     */
    public Donut(Donut other)
    {
        this.flavor = other.flavor;
        this.quantity = other.quantity;
        this.image = other.image;
    }

    /**
     * donut constructor with flavor and image
     * @param flavor
     * @param image
     */
    public Donut(String flavor, int image)
    {
        this.flavor = flavor;
        this.image = image;
    }

    /**
     * get amount of donut or quantity of donut
     * @return
     */
    @Override
    public int getAmount()
    {
        return quantity;
    }

    /**
     * set the amount of donut.
     * @param q
     */
    public void setQuantity(int q)
    {
        quantity = q;
    }

    /**
     * get the amount of donut in an order
     * @return
     */
    @Override
    public double getUnitAmount()
    {
        int index = -1;
        if (YEAST_FLAVORS.contains(flavor)) {
            index = 0;
        } else if (CAKE_FLAVORS.contains(flavor)) {
            index = 1;
        } else if (HOLE_FLAVORS.contains(flavor)) {
            index = 2;
        }
        if (index != -1) {
            return PRICES.get(index);
        } else {
            throw new RuntimeException("unknown type " + flavor);
        }
    }

    /**
     * get the donut flavor user chooses
     * @return
     */
    public String getDonutFlavor()
    {
        return flavor;
    }

    /**
     * get the image of the donut
     * @return
     */
    public int getImage() {
        return image;
    }

    /**
     * Converts donut order into a string printout.
     * @return string representation of the order
     */
    @NonNull
    @Override
    public String toString() {
        String donutEmoji = "\uD83C\uDF69"; // donut emoji unicode
        return String.format(Locale.US, "%s %s (%d) - $%.2f", donutEmoji, flavor, quantity, getCost());
    }

    /**
     * Compares donut objects.
     * Returns true if donut objects are equal, returns false if they are different.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Donut donut = (Donut) obj;
        if (quantity != donut.quantity) {
            return false;
        }
        if (flavor == null) {
            return donut.flavor == null;
        } else {
            return flavor.equals(donut.flavor);
        }
    }
}
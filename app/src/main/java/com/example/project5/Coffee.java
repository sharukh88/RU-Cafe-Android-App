package com.example.project5;

import java.util.ArrayList;
import java.util.List;

/**
 * Coffee class that defines coffee characteristics.
 * Characteristics include cup size, and addin
 * @author Sharukh Khan, Hamad Naveed
 *
 */
public class Coffee extends MenuItem {

    private final List<String> aIns = new ArrayList<>();
    public static final String[] SIZES = new String[]{"Short", "Tall", "Grande", "Venti"};
    private String startSize = "Short";
    private int iQuantity = 1;

    private static final double C_ADDIN_PRICE = 0.30;
    private static final double C_SHORT_PRICE = 1.89;
    private static final double C_TALL_PRICE = 2.09;
    private static final double C_GRANDE_PRICE = 2.69;
    private static final double C_VENTI_PRICE = 3.09;

    /**
     * Coffee constructor
     */
    public Coffee()
    {

    }

    /**
     * Coffee copy constructor
     * @param copy
     */
    public Coffee(Coffee copy)
    {
        this.iQuantity = copy.iQuantity;
        this.startSize = copy.startSize;
        aIns.addAll(copy.aIns);
    }

    /**
     * set size of coffee
     * @param sSize
     */
    public void setSize(String sSize) {
        this.startSize = sSize;
    }

    /**
     * set the amount of coffee user is buying
     * @param iQuantity
     */
    public void setAmount(int iQuantity) {
        this.iQuantity = iQuantity;
    }

    /**
     * add coffee object to bag
     * @param obj
     * @return
     */
    public boolean add(Object obj) {
        if(obj instanceof String sAddin) {
            return aIns.add(sAddin);
        }
        return false;
    }

    /**
     * remove coffee object from bag
     * @param obj
     * @return
     */
    public boolean remove(Object obj) {
        if(obj instanceof String sAddin) {
            return aIns.remove(sAddin);
        }
        return false;
    }

    /**
     * get price of single coffee
     * @return
     */
    @Override
    public double getUnitAmount() {
        return getPrice() + (aIns.size() * C_ADDIN_PRICE);
    }

    /**
     * get price of each size
     * @return
     */
    private double getPrice() {
        if (startSize.equals("Tall")) {
            return C_TALL_PRICE;
        } else if (startSize.equals("Short")) {
            return C_SHORT_PRICE;
        } else if (startSize.equals("Grande")) {
            return C_GRANDE_PRICE;
        } else if (startSize.equals("Venti")) {
            return C_VENTI_PRICE;
        } else {
            throw new RuntimeException("unknown size " + startSize);
        }
    }

    /**
     * get amount/quantity of coffee
     * @return
     */
    @Override
    public int getAmount() {
        return iQuantity;
    }

    /**
     * Converts coffee order into a string printout.
     * @return string representation of the order
     */
    @Override
    public String toString() {
        String description = "☕ Coffee " + startSize + " (" + this.iQuantity + ") " + this.getCostText();
        if(!aIns.isEmpty()) {
            description += " with ";
            for(int i=0; i<aIns.size(); i++) {
                if(i > 0) {
                    description += ", ";
                }
                description += "👉 " + aIns.get(i);
            }
        }
        return description;
    }

    /**
     * Compares coffee objects.
     * Returns true if coffee objects are equal, returns false if they are different.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coffee coffee = (Coffee) obj;
        return iQuantity == coffee.iQuantity && aIns.equals(coffee.aIns) &&
                startSize.equals(coffee.startSize);
    }

}

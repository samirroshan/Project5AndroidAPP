package com.example.project5.enums;

/**
 * Enumeration of the different sizes available for pizzas with associated additional prices.
 * This enum defines the size options for pizzas along with their respective additional cost.
 * The available sizes are SMALL, MEDIUM, and LARGE, each with a specific added cost.
 *
 * SMALL is the base size for a pizza with the base price (8.99).
 * MEDIUM represents a medium-sized pizza with an additional cost of 2.00.
 * LARGE represents a large-sized pizza with an additional cost of 4.00.
 *

 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public enum Size {
    SMALL(8.99),
    MEDIUM(2.00), 
    LARGE(4.00);
    private final double priceAdd;


     Size(double priceAdd) {
        this.priceAdd = priceAdd;
    }


    public double getPriceAdd() {
        return this.priceAdd;
    }
}

package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.util.ArrayList;

/**
 * Represents a Hawaiian pizza.
 * This class extends the Pizza class and provides specific toppings for a Hawaiian pizza.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class Hawaiian extends Pizza {
    private static final double HAWAIIAN_SMALL_PRICE = 12.99;

    /**
     * Constructs a Hawaiian pizza with default settings.
     * The default size is small, with tomato sauce, and specific toppings including ham and pineapple.
     */
    public Hawaiian() {
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.HAM);
        toppings.add(Toppings.PINEAPPLE);
    }
    /**
     * Sets the size of the Hawaiian pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Calculates the price of the Hawaiian pizza based on its size, toppings, and extras.
     *
     * @return The total price of the Hawaiian pizza.
     */
    @Override
    public double price() {
        double price = HAWAIIAN_SMALL_PRICE;
        if (size == Size.MEDIUM) {
            price += Size.MEDIUM.getPriceAdd();
        }
        if (size == Size.LARGE) {
            price += Size.LARGE.getPriceAdd();
        }
        if (extraCheese) {
            price++;
        }
        if (extraSauce) {
            price++;
        }
        return price;
    }

    /**
     * Adds a topping to the Hawaiian pizza.
     * This method is overridden to comply with the abstract method in the Pizza class.
     *
     * @param topping The topping to be added (not applicable for Hawaiian pizza).
     */
    @Override
    public void addToppings(Toppings topping) {

    }
    /**
     * Removes a topping from the Hawaiian pizza.
     * This method is overridden to comply with the abstract method in the Pizza class.
     *
     * @param topping The topping to be removed (not applicable for Hawaiian pizza).
     */

    @Override
    public void removeToppings(Toppings topping) {

    }
    /**
     * Returns a string representation of the Hawaiian pizza.
     *
     * @return A string describing the Hawaiian pizza, including toppings, size, sauce, and extras.
     */

    @Override
    public String toString() {
        String pizzaType = "[Hawaiian] ";
        String toppingsString = "";
        for (Toppings topping : toppings) {
            if (!toppingsString.isEmpty()) {
                toppingsString += ", ";
            }
            toppingsString += BYOActivity.capitalize(topping.name().toLowerCase());
        }
        String sizeString = ", " + size.toString().toLowerCase();
        String sauceString = ", " + sauce.toString().toLowerCase();
        String extraCheeseString = extraCheese ? ", extra cheese" : "";
        String extraSauceString = extraSauce ? ", extra sauce" : "";
        String priceString = " $" + String.format("%.2f", price());
        return pizzaType + toppingsString + sizeString + sauceString + extraCheeseString +
                extraSauceString + priceString;
    }

}

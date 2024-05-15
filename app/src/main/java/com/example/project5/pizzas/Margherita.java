package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.util.ArrayList;

/**
 * Represents a Margherita pizza.
 * This class extends the Pizza class and provides specific toppings for a Margherita pizza.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */

public class Margherita extends Pizza {
    private static final double MARGHERITA_SMALL_PRICE = 13.99;

    /**
     * Constructs a Margherita pizza with default settings.
     * The default size is small, with tomato sauce, and specific toppings including mozzarella, tomatoes, and basil.
     */
    public Margherita() {
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.MOZZARELLA);
        toppings.add(Toppings.TOMATOES);
        toppings.add(Toppings.BASIL);
    }
    /**
     * Calculates the price of the Margherita pizza based on its size, toppings, and extras.
     *
     * @return The total price of the Margherita pizza.
     */

    @Override
    public double price() {
        double price = MARGHERITA_SMALL_PRICE;
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
     * Sets the size of the Margherita pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Adds a topping to the Margherita pizza.
     * This method is overridden to comply with the abstract method in the Pizza class.
     *
     * @param topping The topping to be added (not applicable for Margherita pizza).
     */
    @Override
    public void addToppings(Toppings topping) {

    }
    /**
     * Removes a topping from the Margherita pizza.
     * This method is overridden to comply with the abstract method in the Pizza class.
     *
     * @param topping The topping to be removed (not applicable for Margherita pizza).
     */

    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Returns a string representation of the Margherita pizza.
     *
     * @return A string describing the Margherita pizza, including toppings, size, sauce, and extras.
     */
    @Override
    public String toString() {
        String pizzaType = "[Margherita] ";
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

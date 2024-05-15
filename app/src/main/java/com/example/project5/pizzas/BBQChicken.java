package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.util.ArrayList;

/**
 * Represents a BBQ Chicken pizza.
 * This class extends the Pizza class and implements specific details for a BBQ Chicken pizza.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class BBQChicken extends Pizza {
    private static final double BBQ_CHICKEN_SMALL_PRICE = 15.99;

    /**
     * Constructs a BBQ Chicken pizza with default toppings (chicken, onion, cilantro).
     */
    public BBQChicken() {
        toppings = new ArrayList<>();
        sauce = Sauce.BBQ;
        toppings.add(Toppings.CHICKEN);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.CILANTRO);
    }

    /**
     * Calculates the price of the BBQ Chicken pizza based on its size and extras.
     *
     * @return The total price of the BBQ Chicken pizza.
     */
    @Override
    public double price() {
        double price = BBQ_CHICKEN_SMALL_PRICE;
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
     * Sets the size of the BBQ Chicken pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Adds a topping to the BBQ Chicken pizza (Not applicable for pre-defined pizzas).
     *
     * @param topping The topping to be added.
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removes a topping from the BBQ Chicken pizza (Not applicable for pre-defined pizzas).
     *
     * @param topping The topping to be removed.
     */
    @Override
    public void removeToppings(Toppings topping) {

    }
    /**
     * Returns a string representation of the BBQ Chicken pizza.
     *
     * @return A string describing the BBQ Chicken pizza, including toppings, size, sauce, and extras.
     */
    @Override
    public String toString() {
        String pizzaType = "[BBQ Chicken] ";
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

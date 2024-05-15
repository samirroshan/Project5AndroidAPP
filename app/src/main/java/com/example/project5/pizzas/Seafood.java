package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents a Seafood pizza, a specific type of pizza with seafood toppings.
 * This class extends the abstract Pizza class, providing specific details and behaviors for Seafood pizzas.
 * Seafood pizzas have a base price, and additional charges may apply for larger sizes, extra cheese, and extra sauce.
 * The toppings include shrimp, squid, and crabmeat, with Alfredo sauce as the default sauce.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class Seafood extends Pizza{
    private static final double SEAFOOD_SMALL_PRICE = 17.99;

    /**
     * Constructs a Seafood pizza with default settings, including toppings and sauce.
     */

    public Seafood() {
        toppings = new ArrayList<>();
        sauce = Sauce.ALFREDO;
        toppings.add(Toppings.SHRIMP);
        toppings.add(Toppings.SQUID);
        toppings.add(Toppings.CRABMEAT);
    }

    /**
     * Calculates the total price of the Seafood pizza based on size, toppings, and extras.
     *
     * @return The total price of the Seafood pizza.
     */
    @Override
    public double price() {
        double price = SEAFOOD_SMALL_PRICE;
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }
        if(size == Size.LARGE){
            price += Size.LARGE.getPriceAdd();
        }
        if(extraCheese){
            price++;
        }
        if(extraSauce){
            price++;
        }
        return price;
    }
    /**
     * Adds a topping to the Seafood pizza. This method is not supported for Seafood pizzas.
     *
     * @param topping The topping to be added (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removes a topping from the Seafood pizza. This method is not supported for Seafood pizzas.
     *
     * @param topping The topping to be removed (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }
    /**
     * Sets the size of the Seafood pizza.
     *
     * @param newSize The new size of the Seafood pizza.
     */

    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Provides a string representation of the Seafood pizza, including details such as toppings,
     * size, sauce, extras, and the total price.
     *
     * @return String representing the details of the Seafood pizza.
     */
    @Override
    public String toString() {
        String pizzaType = "[Seafood] ";
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

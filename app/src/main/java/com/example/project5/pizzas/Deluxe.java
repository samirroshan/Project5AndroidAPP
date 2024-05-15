package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents a Deluxe pizza.
 * This class extends the Pizza class and provides specific toppings for a Deluxe pizza.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class Deluxe extends Pizza{

    private static final double DELUXE_SMALL_PRICE = 14.99;
    /**
     * Constructs a Deluxe pizza with default settings.
     * The default size is small, with tomato sauce, and specific toppings including sausage, pepperoni,
     * green pepper, onion, and mushroom.
     */
    public Deluxe() {
        toppings = new ArrayList<>();
        this.size = Size.SMALL;
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.GREENPEPPER);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.MUSHROOM);
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size, toppings, and extras.
     *
     * @return The total price of the Deluxe pizza.
     */
    @Override
    public double price() {
        double price = DELUXE_SMALL_PRICE;
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }else
        if(size == Size.LARGE){
            price += Size.LARGE.getPriceAdd();
        }
        if(extraCheese){
            price++;
        }
        if(extraSauce) {
            price++;
        }
        return price;
    }
    /**
     * Adds a topping to the Deluxe pizza.
     * This method is overridden to comply with the abstract method in the Pizza class.
     *
     * @param topping The topping to be added (not applicable for Deluxe pizza).
     */

    @Override
    public void addToppings(Toppings topping) {

    }
    /**
     * Removes a topping from the Deluxe pizza.
     * This method is overridden to comply with the abstract method in the Pizza class.
     *
     * @param topping The topping to be removed (not applicable for Deluxe pizza).
     */

    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Sets the size of the Deluxe pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Returns a string representation of the Deluxe pizza.
     *
     * @return A string describing the Deluxe pizza, including toppings, size, sauce, and extras.
     */
    @Override
    public String toString() {
        String pizzaType = "[Deluxe] ";
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

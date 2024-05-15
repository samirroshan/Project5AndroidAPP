package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents a Meatzza pizza.
 * This class extends the Pizza class and provides specific toppings for a Meatzza pizza.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class Meatzza extends Pizza{

    private static final double MEATZZA_SMALL_PRICE = 16.99;

    /**
     * Constructs a Meatzza pizza with default settings.
     * The default size is small, with tomato sauce, and specific toppings including sausage, pepperoni, beef, and ham.
     */
    public Meatzza(){
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.BEEF);
        toppings.add(Toppings.HAM);
    }
    /**
     * Calculates the price of the Meatzza pizza based on its size, toppings, and extras.
     *
     * @return The total price of the Meatzza pizza.
     */

    @Override
    public double price() {
        double price = MEATZZA_SMALL_PRICE;
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
     * Adds a topping to the Meatzza pizza.
     * This method is overridden to comply with the abstract method in the Pizza class.
     *
     * @param topping The topping to be added (not applicable for Meatzza pizza).
     */

    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removes a topping from the Meatzza pizza.
     * This method is overridden to comply with the abstract method in the Pizza class.
     *
     * @param topping The topping to be removed (not applicable for Meatzza pizza).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Sets the size of the Meatzza pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }
    /**
     * Returns a string representation of the Meatzza pizza.
     *
     * @return A string describing the Meatzza pizza, including toppings, size, sauce, and extras.
     */
    @Override
    public String toString() {
        String pizzaType = "[Meatzza] ";
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

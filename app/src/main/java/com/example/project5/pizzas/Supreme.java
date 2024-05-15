package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents a Supreme pizza, a specific type of pizza with a variety of toppings.
 * This class extends the abstract Pizza class, providing specific details and behaviors for Supreme pizzas.
 * Supreme pizzas have a base price, and additional charges may apply for larger sizes, extra cheese, and extra sauce.
 * The toppings include sausage, pepperoni, ham, green pepper, onion, black olive, and mushroom, with tomato sauce as the default sauce.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class Supreme extends Pizza{

    private static final double SUPREME_SMALL_PRICE = 15.99;

    /**
     * Constructs a Supreme pizza with default settings, including toppings and sauce.
     */
    public Supreme(){
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.HAM);
        toppings.add(Toppings.GREENPEPPER);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.BLACKOLIVE);
        toppings.add(Toppings.MUSHROOM);
    }

    /**
     * Calculates the total price of the Supreme pizza based on size, toppings, and extras.
     *
     * @return The total price of the Supreme pizza.
     */
    @Override
    public double price() {
        double price = SUPREME_SMALL_PRICE;
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
     * Adds a topping to the Supreme pizza. This method is not supported for Supreme pizzas.
     *
     * @param topping The topping to be added (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removes a topping from the Supreme pizza. This method is not supported for Supreme pizzas.
     *
     * @param topping The topping to be removed (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }
    /**
     * Sets the size of the Supreme pizza.
     *
     * @param newSize The new size of the Supreme pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Provides a string representation of the Supreme pizza, including details such as toppings,
     * size, sauce, extras, and the total price.
     *
     * @return String representing the details of the Supreme pizza.
     */
    @Override
    public String toString() {
        String pizzaType = "[Supreme] ";
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

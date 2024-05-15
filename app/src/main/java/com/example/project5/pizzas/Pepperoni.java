package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents a Pepperoni pizza.
 * This class extends the Pizza class and provides specific toppings for a Pepperoni pizza.
 * It is designed to be a single-topping pizza with pepperoni as the fixed topping.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class Pepperoni extends Pizza{

    private static final double PEPPERONI_SMALL_PRICE = 10.99;

    /**
     * Constructs a Pepperoni pizza with default settings.
     * The default size is small, with tomato sauce, and the fixed topping is pepperoni.
     */
    public Pepperoni(){
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.PEPPERONI);
    }
    /**
     * Calculates the price of the Pepperoni pizza based on its size, toppings, and extras.
     *
     * @return The total price of the Pepperoni pizza.
     */
    @Override
    public double price() {
        double price = PEPPERONI_SMALL_PRICE;
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
     * Adding additional toppings is not supported for the Pepperoni pizza, as it is
     * designed to be a single-topping pizza.
     *
     * @param topping The topping that the user attempts to add (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removing the existing topping is not supported for the Pepperoni pizza,
     * as it is a core feature of this pizza type.
     *
     * @param topping The topping that the user attempts to remove (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Sets the size of the Pepperoni pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Provides a string representation of the Pepperoni pizza.
     * The implementation should ideally include details such as the type of pizza,
     * the list of fixed toppings, size, and sauce,
     * followed by the total price. However, it currently returns null and needs to be
     * properly implemented.
     *
     * @return String representing the details of the Pepperoni pizza
     */
    @Override
    public String toString() {
        String pizzaType = "[Pepperoni] ";
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

package com.example.project5.pizzas;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents an abstract Pizza class that serves as the base for various pizza types.
 * Concrete pizza types should extend this class and provide implementations for specific pizza variations.
 * This class defines common properties and behaviors for pizzas, such as toppings, size, sauce, and extras.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */

public abstract class Pizza {
    protected ArrayList<Toppings> toppings; //Topping is a enum class
    protected Size size; //Size is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Abstract method to calculate the price of the pizza.
     * Concrete implementations should provide the specific price calculation logic.
     *
     * @return The total price of the pizza.
     */

    public abstract double price(); //polymorphism

    /**
     * Abstract method to add a topping to the pizza.
     *
     * @param topping The topping to be added.
     */

    public abstract void addToppings(Toppings topping);

    /**
     * Abstract method to remove a topping from the pizza.
     *
     * @param topping The topping to be removed.
     */
    public abstract void removeToppings(Toppings topping);

    /**
     * Sets the size of the pizza.
     *
     * @param newSize The new size of the pizza.
     */

    public void setSize(Size newSize) {
    }

    /**
     * Sets the sauce of the pizza.
     *
     * @param newSauce The new sauce of the pizza.
     */
    public void setSauce(Sauce newSauce) {
    }
    /**
     * Sets the flag indicating whether extra cheese is added to the pizza.
     *
     * @param extraCheese True if extra cheese is added, false otherwise.
     */

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    /**
     * Sets the flag indicating whether extra sauce is added to the pizza.
     *
     * @param extraSauce True if extra sauce is added, false otherwise.
     */
    public void setExtraSauce(boolean extraSauce){
        this.extraSauce = extraSauce;
    }

    /**
     * Gets the list of toppings on the pizza.
     *
     * @return The list of toppings.
     */

    public ArrayList<Toppings> getToppings() {
        return toppings;
    }
    /**
     * Gets the size of the pizza.
     *
     * @return The size of the pizza.
     */
    public Size getSize(){return size;}

    /**
     * Gets the sauce of the pizza.
     *
     * @return The sauce of the pizza.
     */
    public Sauce getSauce() {
        return sauce;
    }
    /**
     * Abstract method to provide a string representation of the pizza.
     * Concrete implementations should override this method to include details such as toppings,
     * size, sauce, and extras, followed by the total price.
     *
     * @return String representing the details of the pizza.
     */

    @Override
    public abstract String toString();
}

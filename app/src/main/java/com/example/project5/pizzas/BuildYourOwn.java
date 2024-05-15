package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

/**
 * Represents a Build Your Own pizza.
 * This class extends the Pizza class and provides customization options for a pizza.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class BuildYourOwn extends Pizza{
    private static final int MAX_TOPPINGS = 3;
    private ArrayList<Toppings> toppings;
    private static final double EXTRA_TOPPING_COST = 1.49;

    /**
     * Constructs a Build Your Own pizza with default settings.
     * The default size is small, with tomato sauce, and no extra cheese or sauce.
     */
    public BuildYourOwn() {
        this.toppings = new ArrayList<>();
        this.size = Size.SMALL;
        this.sauce = Sauce.TOMATO;
        this.extraCheese = false;
        this.extraSauce = false;
    }

    /**
     * Sets the size of the Build Your Own pizza.
     *
     * @param size The new size of the pizza.
     */
    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Sets the sauce of the Build Your Own pizza.
     *
     * @param sauce The new sauce of the pizza.
     */

    @Override
    public void setSauce(Sauce sauce){
        this.sauce = sauce;
    }

    /**
     * Calculates the price of the Build Your Own pizza based on its size, toppings, and extras.
     *
     * @return The total price of the Build Your Own pizza.
     */
    @Override
    public double price() {
        double price = Size.SMALL.getPriceAdd();
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }else
        if(size == Size.LARGE){
            price += Size.LARGE.getPriceAdd();
        }
        if(toppings.size() > MAX_TOPPINGS){
            price += (toppings.size() - MAX_TOPPINGS) * EXTRA_TOPPING_COST;
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
     * Adds a topping to the Build Your Own pizza, limited to a maximum of three toppings.
     *
     * @param topping The topping to be added.
     */

    @Override
    public void addToppings(Toppings topping){
        toppings.add(topping);
    }

    /**
     * Removes a topping from the Build Your Own pizza.
     *
     * @param topping The topping to be removed.
     */

    @Override
    public void removeToppings(Toppings topping){

        toppings.remove(topping);
    }

    /**
     * Returns a string representation of the Build Your Own pizza.
     *
     * @return A string describing the Build Your Own pizza, including toppings, size, sauce, and extras.
     */
    @Override
    public String toString() {
        String pizzaType = "[Build Your Own] ";
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
        System.out.println("Extras - Cheese: " + extraCheese + ", Sauce: " + extraSauce);
        return pizzaType + toppingsString + sizeString + sauceString + extraCheeseString + extraSauceString + priceString;
    }
}


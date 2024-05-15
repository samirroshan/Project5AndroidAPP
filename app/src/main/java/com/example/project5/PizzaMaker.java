package com.example.project5;

import com.example.project5.pizzas.BBQChicken;
import com.example.project5.pizzas.BuildYourOwn;
import com.example.project5.pizzas.Deluxe;
import com.example.project5.pizzas.Hawaiian;
import com.example.project5.pizzas.Margherita;
import com.example.project5.pizzas.Mexican;
import com.example.project5.pizzas.Supreme;
import com.example.project5.pizzas.Meatzza;
import com.example.project5.pizzas.Seafood;
import com.example.project5.pizzas.Pepperoni;
import com.example.project5.pizzas.Pizza;
import com.example.project5.pizzas.Veggie;

/**
 * PizzaMaker is a utility class for creating various pizza objects based on the specified pizza type.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class PizzaMaker {


    /**
     * Creates a pizza object based on the specified pizza type.
     *
     * @param pizzaType The type of pizza to create.
     * @return A pizza object of the specified type.
     * @throws IllegalArgumentException If the provided pizza type is unknown.
     */

    public static Pizza createPizza(String pizzaType) {
        switch (pizzaType) {
            case "BYO":
                return new BuildYourOwn();
            case "Hawaiian":
                return new Hawaiian();
            case "Supreme":
                return new Supreme();
            case "Meatzza":
                return new Meatzza();
            case "Seafood":
                return new Seafood();
            case "Pepperoni":
                return new Pepperoni();
            case "BBQ Chicken":
                return new BBQChicken();
            case "Deluxe":
                return new Deluxe();
            case "Margherita":
                return new Margherita();
            case "Veggie":
                return new Veggie();
            case "Mexican":
                return new Mexican();
            default:
                throw new IllegalArgumentException("Unknown pizza type: " + pizzaType);
        }
    }
}

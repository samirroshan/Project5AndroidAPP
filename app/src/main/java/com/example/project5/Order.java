package com.example.project5;

import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

/**
 * Represents an order containing a list of pizzas for the pizza ordering application.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class Order {
    private int orderNum;
    private ArrayList<Pizza> pizzaOrders;
    private StoreOrders storeOrders= StoreOrders.getInstance();
    private static Order currOrderInstance;
    private static final double SALES_TAX_RATE = 0.06625;

    /**
     * Constructs a new Order instance with a unique order number and an empty list of pizzas.
     */
    private Order() {
        this.orderNum = StoreOrders.getInstance().getNextOrderNum();
        pizzaOrders = new ArrayList<>();
    }

    /**
     * Gets the current instance of the Order class using the Singleton pattern.
     *
     * @return The current instance of the Order class.
     */
    public static Order getInstance() {
        if (currOrderInstance == null) {
            currOrderInstance = new Order();
        }
        return currOrderInstance;
    }

    /**
     * Adds a pizza to the order.
     *
     * @param pizza The pizza to be added to the order.
     */

    public void addToOrder(Pizza pizza){
        pizzaOrders.add(pizza);
    }

    /**
     * Creates a new instance of the Order class, effectively starting a new order.
     */
    public static void createNewOrder(){
        currOrderInstance = new Order();
    }

    /**
     * Gets the order number associated with this order.
     *
     * @return The order number.
     */
    public int getOrderNum() { return orderNum; }

    /**
     * Gets the list of pizzas in the order.
     *
     * @return The list of pizzas in the order.
     */
    public ArrayList<Pizza> getPizzas(){return pizzaOrders;}

    /**
     * Removes a pizza from the order based on its index.
     *
     * @param pizzaIndex The index of the pizza to be removed.
     * @return True if the pizza was successfully removed, false otherwise.
     */
    public boolean removePizza(int pizzaIndex){
        if (pizzaIndex >= 0 && pizzaIndex < pizzaOrders.size()) {
            pizzaOrders.remove(pizzaIndex);
            return true;
        }
        return false;
    }

    /**
     * Calculates and gets the total cost of the order, including sales tax.
     *
     * @return The total cost of the order formatted as a string.
     */
    public String getOrderTotal() {
        double subTotal = 0;
        for (Pizza pizza : pizzaOrders) {
            if (pizza != null) {
                subTotal += pizza.price();
            }
        }
        double salesTax = Math.round((subTotal*SALES_TAX_RATE)*100.0)/100.0;
        double totalCost = Math.round((subTotal + salesTax)*100.0)/100.0;
        return String.format("%.2f", totalCost);
    }



}

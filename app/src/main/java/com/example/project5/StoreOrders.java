package com.example.project5;

import java.util.ArrayList;

/**
 * StoreOrders represents a singleton class responsible for managing store orders.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class StoreOrders {

    private static StoreOrders instance;
    private ArrayList<Order> storeOrders;
    private static int nextOrderNum;
    private static final int NOT_FOUND = -1;


    /**
     * Private constructor to enforce singleton pattern and initialize variables.
     */
    private StoreOrders(){
        nextOrderNum = 1;
        storeOrders = new ArrayList<>();
    }
    /**
     * Returns the singleton instance of StoreOrders.
     *
     * @return The singleton instance of StoreOrders.
     */
    public static StoreOrders getInstance() {
        if (instance == null) {
            instance = new StoreOrders();
        }
        return instance;
    }
    /**
     * Retrieves the next available order number.
     *
     * @return The next available order number.
     */

    public int getNextOrderNum(){
        return nextOrderNum;
    }

    /**
     * Retrieves the list of store orders.
     *
     * @return The list of store orders.
     */
    public ArrayList<Order> getOrders(){
        return storeOrders;
    }

    /**
     * Adds an order to the store's order history.
     *
     * @param order The Order object to be added.
     * @return True if the order is added successfully; false otherwise.
     */
    public boolean addOrder(Order order){
        if(order != null && !order.getPizzas().isEmpty()){
            storeOrders.add(order);
            nextOrderNum++;
            return true;
        }
        return false;
    }

    /**
     * Checks if the store's order history is empty.
     *
     * @return True if there are no orders in the store's order history; false otherwise.
     */
    public boolean storeOrdersEmpty(){
        return storeOrders.isEmpty();
    }

    /**
     * Removes a specified order from the store's order history.
     *
     * @param order The Order object to be removed.
     * @return True if the order is removed successfully; false otherwise.
     */
    public boolean removeOrder(Order order){
        if(order != null){
            storeOrders.remove(order);
            return true;
        } else
            return false;
    }
    /**
     * Retrieves an order by its order number.
     *
     * @param id The order number of the desired order.
     * @return The Order object with the specified order number; null if not found.
     */
    public Order getOrderById(int id) {
        for (Order order : storeOrders) {
            if (order.getOrderNum() == id) {
                return order;
            }
        }
        return null;
    }


}

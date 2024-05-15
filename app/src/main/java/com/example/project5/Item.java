package com.example.project5;

import com.example.project5.enums.Toppings;
import com.example.project5.enums.Sauce;

import java.util.ArrayList;

/**
 * Represents an item in the menu with its properties, such as name, image, unit price, toppings, and sauce.
 *
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 * @see Toppings
 * @see Sauce
 */

public class Item {
    private String itemName;
    private int image;
    private String unitPrice; //for demo purpose, the unitPrice is of String type
    private ArrayList<Toppings> toppings;
    private Sauce sauce;

    /**
     * Constructs a new Item with the specified properties.
     *
     * @param itemName  The name of the item.
     * @param image     The resource ID of the item's image.
     * @param unitPrice The unit price of the item (for demo purposes, represented as a String).
     * @param toppings  The list of toppings associated with the item.
     * @param sauce     The sauce associated with the item.
     */
    public Item(String itemName, int image, String unitPrice, ArrayList<Toppings> toppings, Sauce sauce) {
        this.itemName = itemName;
        this.image = image;
        this.unitPrice = unitPrice;
        this.toppings = toppings;
        this.sauce = sauce;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets the resource ID of the item's image.
     *
     * @return The resource ID of the item's image.
     */
    public int getImage() {
        return image;
    }

    /**
     * Gets the unit price of the item.
     *
     * @return The unit price of the item.
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * Gets the sauce associated with the item.
     *
     * @return The sauce associated with the item.
     */
    public Sauce getSauce() {
        return sauce;
    }

    /**
     * Sets the sauce associated with the item.
     *
     * @param sauce The sauce to set for the item.
     */
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Converts the list of toppings to a string representation.
     *
     * @return A string representation of the toppings associated with the item.
     */
    public String toppingsToString() {return toppings.toString();}
}



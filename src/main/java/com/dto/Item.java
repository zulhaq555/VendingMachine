package com.dto;

import java.util.ArrayList;

/**
 * @author Zain Ul-Haq
 * This class is the Item DAO, creates instances of items and stores them in an item arraylist,
 * also reads/writes items to a file.
 */
public class Item {

    private String itemName;
    private int itemPrice;
    private int itemStock;

    //The arraylist that contains the items from file
    public static ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Main constructor for items
     *
     * @param itemName  Name of the item
     * @param itemPrice The price of the item
     * @param itemStock The number of items available for purchase
     */
    public Item(String itemName, int itemPrice, int itemStock) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        items.add(this);
    }

    /**
     * @return Name of item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @return Price of item
     */
    public int getItemPrice() {
        return itemPrice;
    }

    /**
     * @return Number of items in stock
     */
    public int getItemStock() {
        return itemStock;
    }

    /**
     * Decrements the number of specific item in stock.
     *
     * @param itemStock The amount of items to be deducted (will always be 1 in this case).
     */
    public void setItemStock(int itemStock) {
        this.itemStock -= itemStock;
    }

    /**
     * @return The selected items details
     */
    @Override
    public String toString() {
        return "Item{" +
                "itemName = " + itemName +
                " | itemPrice = " + itemPrice +
                " | itemStock = " + itemStock +
                '}';
    }
}

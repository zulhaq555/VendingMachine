package com.ui;

import com.dto.Item;

import java.math.BigDecimal;

public class VendingView {
    private UserIO io;

    public VendingView(UserIO io){
        this.io = io;
    }

    public void welcome(){
        io.print("Welcome to Vending Machine!");
    }

    public void exit(){
        io.print("Thank You!\nGood Bye!!!");
    }

    public void displayChange(BigDecimal totalChange, BigDecimal[] numOfCoins){
        //Outputs total change and number of each coin.
        io.print("Total Change: £" + totalChange);
        io.print("Change Dispensed:\nTwo Pound Coins: " + numOfCoins[0]
                + "\nOne Pound Coins: " + numOfCoins[1]
                + "\nFifty Pence Coins: " + numOfCoins[2]
                + "\nTwenty Pence Coins: " + numOfCoins[3]
                + "\nTen Pence Coins: " + numOfCoins[4]
                + "\nFive Pence Coins: " + numOfCoins[5]
                + "\nTwo Pence Coins: " + numOfCoins[6]
                + "\nOne Pence Coins: " + numOfCoins[7]);
    }

    /**
     * Uses a lambda function to display all items in the arraylist that are in stock
     */
    public void listItems(){
        Item.items.stream()
                .filter(item -> item.getItemStock() > 0)
                .forEach(System.out::println);
    }

    public double getBalance(String prompt){
        return io.readDouble(prompt);
    }

    public String getItem(String prompt){
        return io.readString(prompt);
    }

}

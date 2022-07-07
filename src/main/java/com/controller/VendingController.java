package com.controller;

import com.customExceptions.InsufficientFundsException;
import com.customExceptions.ItemNonExistentException;
import com.customExceptions.NoItemInventoryException;
import com.dto.Audit;
import com.dto.Coins;
import com.dto.Item;
import com.service.AuditService;
import com.service.ItemService;
import com.ui.VendingView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingController {

    private ItemService itemService;
    private AuditService auditService;
    private VendingView view;
    private BigDecimal balance;

    public VendingController(ItemService itemService, AuditService auditService, VendingView view){
        this.itemService = itemService;
        this.auditService = auditService;
        this.view = view;
    }

    public void startController(){
        view.welcome();
        itemService.readItems();
        auditService.readAudits();
        view.listItems();

        setBalance(view.getBalance("Input Money(£): "));


        //Converts balance from pennies to pounds
        BigDecimal balance2 = balance.divide(BigDecimal.valueOf(100));
        BigDecimal balance3 = balance2.setScale(2, RoundingMode.DOWN);

        while(true){

            String itemName = view.getItem("Enter item name or Exit: ");

            if (itemName.equalsIgnoreCase("exit")){
                break;
            }

            try {

                //Checks if item exists
                if (!isItemExist(itemName)){
                    throw new ItemNonExistentException("Item does not exist!");
                }

                //Checks if item is in stock
                if (!isInStock(itemName)){
                    throw new NoItemInventoryException("Item is out of stock!");
                }

                //Checks if user has enough to buy item
                if (!isSufficientFunds(itemName)){
                    throw new InsufficientFundsException("Insufficient Balance!");
                }


                //If all previous conditions are true then item will be bought
                for (Item i: Item.items){
                    if (i.getItemName().equalsIgnoreCase(itemName)){
                        BigDecimal cost = new BigDecimal(i.getItemPrice());

                        //Decrements item stock
                        i.setItemStock(1);

                        //Creates a new audit as item stock has decreased
                        Audit audit = new Audit(i.getItemName());

                        //Calculates and returns change
                        calculateChange(cost);
                    }
                }
                break;

            }catch (NoItemInventoryException e){
                //Will print following message, print current balance in pounds and prompt user to choose new item
                System.out.println(e);
                System.out.println("Current Balance: £" + balance3);
            }catch (InsufficientFundsException e){
                //Will print following message.
                System.out.println(e);

                int min = Item.items.get(0).getItemPrice();
                for (Item i: Item.items){
                    if (min > i.getItemPrice()){
                        min = i.getItemPrice();
                    }
                }

                //If user cannot afford any items then following message will be printed and system will terminate
                BigDecimal minimum = new BigDecimal(min);
                if (balance.compareTo(minimum) < 0){
                    System.out.println("You cannot afford any of these items!\nReturning Money!");
                    break;
                }

                System.out.println("Current Balance: £" + balance3);
            }catch (ItemNonExistentException e){
                System.out.println(e);
            }
        }

        itemService.writeItems();
        auditService.writeAudits();

        view.exit();

    }

    /**
     * Prompts the user to input their money
     */
    public void setBalance(double input){
        double inputBalance = input;
        inputBalance *= 100;
        balance = new BigDecimal(inputBalance);
    }

    /**
     * Checks if an item inputted by user exists or not
     * @param itemName Name of item inputted by user
     * @return whether item exists or not
     */
    public boolean isItemExist(String itemName){
        for (Item i: Item.items){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a user had enough for selected item
     * @param itemName The name of the selected item
     * @return Whether user can afford or not
     */
    public boolean isSufficientFunds(String itemName){
        for (Item i: Item.items){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                BigDecimal itemPrice = new BigDecimal(i.getItemPrice());
                return balance.compareTo(itemPrice) >= 0;
            }
        }
        return false;
    }

    /**
     * Checks if selected item is in stock
     * @param itemName Name of selected item
     * @return Whether item is in stock or not
     */
    public boolean isInStock(String itemName){
        for (Item i: Item.items){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                return i.getItemStock() > 0;
            }
        }
        return false;
    }

    /**
     * Will output total change and the number of each type of coin
     * @param cost The cost of the item bought
     */
    public void calculateChange(BigDecimal cost){

        //Calculates the change, and converts the change from pennies to pounds
        BigDecimal change = balance.subtract(cost);
        BigDecimal change2 = change.divide(BigDecimal.valueOf(100));
        BigDecimal change3 = change2.setScale(2, RoundingMode.DOWN);

        //Creates and array of Coins
        Coins[] coins = {Coins.TWO_POUND, Coins.POUND, Coins.FIFTY, Coins.TWENTY, Coins.TEN, Coins.FIVE, Coins.TWO, Coins.ONE};
        //Array where number of each coin will be stored
        BigDecimal[] numOfCoins = new BigDecimal[8];

        //Loops through each coin value and calculates the number of each coin
        for (int i = 0; i < numOfCoins.length; i++) {
            BigDecimal[] values = change.divideAndRemainder(BigDecimal.valueOf(coins[i].getCoinValue()));
            numOfCoins[i] = values[0];
            change = values[1];
        }

        //Outputs total change and number of each coin.
        view.displayChange(change3, numOfCoins);

    }
}

package com.dto;

/**
 * @author Zain Ul-Haq
 * This enum represents the coins that can be returned to the user as change.
 */
public enum Coins {

    //Each of the coins that can be returned to the user
    TWO_POUND(200), POUND(100), FIFTY(50), TWENTY(20), TEN(10), FIVE(5), TWO(2), ONE(1);

    //The value of the coin in pennies
    private int coinValue;

    /**
     * @param c The value of the coin in pennies
     */
    Coins(int c){
        this.coinValue = c;
    }

    /**
     * @return The value of the coin
     */
    public int getCoinValue() {
        return coinValue;
    }
}

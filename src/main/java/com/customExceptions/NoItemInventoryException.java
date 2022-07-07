package com.customExceptions;

/**
 * @author Zain Ul-Haq
 * Will be thrown when user selects item that is out of stock.
 */
public class NoItemInventoryException extends Exception{
    public NoItemInventoryException(String errorMsg){
        super(errorMsg);
    }
}

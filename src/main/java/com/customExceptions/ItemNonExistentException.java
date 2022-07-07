package com.customExceptions;

/**
 * @author Zain Ul-Haq
 * Will be called when a user selects an item that does not exist
 */
public class ItemNonExistentException extends Exception{
    public ItemNonExistentException(String errorMsg){
        super(errorMsg);
    }
}

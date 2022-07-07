package com.customExceptions;

/**
 * @author Zain Ul-Haq
 * Exception will be thrown when user doesn't have enough money for purchase.
 */
public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String errorMsg){
        super(errorMsg);
    }
}

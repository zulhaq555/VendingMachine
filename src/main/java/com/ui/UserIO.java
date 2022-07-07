package com.ui;

public interface UserIO {
    void print(String message);

    double readDouble(String prompt);

    String readString(String prompt);
}

package com.ui;

import java.util.Scanner;

public class UserIOImpl implements UserIO{
    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }
}

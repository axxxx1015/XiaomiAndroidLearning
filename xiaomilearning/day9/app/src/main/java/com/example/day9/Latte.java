package com.example.day9;

public class Latte extends Coffee {
    @Override
    public String getDescription() {
        return "拿铁";
    }
    @Override
    public double cost() {
        return 18.0;
    }
} 
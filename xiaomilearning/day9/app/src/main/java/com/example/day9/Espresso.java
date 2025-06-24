package com.example.day9;

public class Espresso extends Coffee {
    @Override
    public String getDescription() {
        return "意式浓缩";
    }
    @Override
    public double cost() {
        return 15.0;
    }
} 
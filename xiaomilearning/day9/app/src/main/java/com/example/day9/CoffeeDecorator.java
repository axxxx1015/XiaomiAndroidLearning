package com.example.day9;

public abstract class CoffeeDecorator extends Coffee {
    protected Coffee coffee;
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
} 
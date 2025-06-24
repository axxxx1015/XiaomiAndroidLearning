package com.example.day9;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return coffee.getDescription() + "+糖";
    }
    @Override
    public double cost() {
        return coffee.cost() + 2.0;
    }
} 
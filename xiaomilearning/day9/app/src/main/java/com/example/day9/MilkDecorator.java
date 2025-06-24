package com.example.day9;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return coffee.getDescription() + "+牛奶";
    }
    @Override
    public double cost() {
        return coffee.cost() + 3.0;
    }
} 
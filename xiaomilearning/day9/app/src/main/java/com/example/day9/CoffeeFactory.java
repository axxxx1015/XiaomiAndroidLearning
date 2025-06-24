package com.example.day9;

public class CoffeeFactory {
    public static Coffee createCoffee(String type) {
        switch (type) {
            case "Espresso":
                return new Espresso();
            case "Latte":
                return new Latte();
            default:
                throw new IllegalArgumentException("未知咖啡类型: " + type);
        }
    }
} 
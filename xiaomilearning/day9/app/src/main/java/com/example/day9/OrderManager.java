package com.example.day9;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static volatile OrderManager instance;
    private final List<OrderObserver> observers = new ArrayList<>();

    private OrderManager() {}
    public static OrderManager getInstance() {
        if (instance == null) {
            synchronized (OrderManager.class) {
                if (instance == null) {
                    instance = new OrderManager();
                }
            }
        }
        return instance;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    public void notifyOrderStatusChanged(Order order) {
        for (OrderObserver observer : observers) {
            observer.onOrderStatusChanged(order);
        }
    }
} 
package com.example.day9;

public class Order {
    public enum Status {
        CREATED, PAID, MAKING, FINISHED
    }
    private final String coffeeName;
    private Status status;
    public Order(String coffeeName) {
        this.coffeeName = coffeeName;
        this.status = Status.CREATED;
    }
    public String getCoffeeName() {
        return coffeeName;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
} 
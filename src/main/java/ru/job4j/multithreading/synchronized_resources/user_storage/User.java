package ru.job4j.multithreading.synchronized_resources.user_storage;

public class User {
    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }
}

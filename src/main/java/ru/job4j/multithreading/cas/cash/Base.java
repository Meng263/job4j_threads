package ru.job4j.multithreading.cas.cash;

public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
    }

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Base copyAndIncVersion() {
        return new Base(this.id, this.version + 1, this.name);
    }
}
package com.entity;

public class Screen {

    private String id;
    private String name; // e.g., "Screen 1", "Screen 2"
    private int capacity;

    public Screen(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

}

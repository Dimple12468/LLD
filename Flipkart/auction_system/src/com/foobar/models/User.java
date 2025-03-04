package com.foobar.models;

public abstract class User {
    protected String name;
    // id, bidamt, winner/looser

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

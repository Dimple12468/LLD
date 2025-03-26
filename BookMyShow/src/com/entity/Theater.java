package com.entity;

import java.util.HashMap;
import java.util.Map;

public class Theater {

    private String id;
    private String name;
    private String location;
    private Map<String, Screen> screens; // ScreenId -> Screen

    public Theater(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.screens = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Screen> getScreens() {
        return screens;
    }

    public void addScreen(Screen screen) {
        screens.put(screen.getId(), screen);
    }
}
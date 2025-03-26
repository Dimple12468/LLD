package com.entity;

public class Movie {

    private String id;
    private String title;
    private String duration;
    private String language;

    public Movie(String id, String title, String duration, String language) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}

package com.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Show {

    private String id;
    private Movie movie;
    private Theater theater;
    private Screen screen;
    private Date startTime;
    private Map<String, Seat> seats;

    public Show(String id, Movie movie, Theater theater, Screen screen, Date startTime) {
        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
        this.seats = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public Screen getScreen() {
        return screen;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }
}

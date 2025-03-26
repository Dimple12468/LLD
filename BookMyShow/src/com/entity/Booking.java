package com.entity;

import java.util.List;

public class Booking {

    private String id;
    private User user;
    private Show show;
    private List<Seat> bookedSeats;
    private Payment payment;

    public Booking(String id, User user, Show show, List<Seat> bookedSeats, Payment payment) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Payment getPayment() {
        return payment;
    }
}

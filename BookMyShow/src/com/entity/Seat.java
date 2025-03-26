package com.entity;

import com.util.SeatStatus;
import com.util.SeatType;

public class Seat {

    private String id;
    private SeatType type;
    private SeatStatus status;
    private double price;

    public Seat(String id, SeatType type, double price) {
        this.id = id;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }
}

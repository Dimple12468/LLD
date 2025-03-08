package com.problem.entity;

import java.util.HashMap;
import java.util.List;

public class Patient extends User {

    // using id in case two or more pateints have same name
    // id and name coming from User
    protected HashMap<Doctor, List<TimeSlot>> bookedSlots;

    public Patient(String id, String name) {
        super(id, name);
        this.bookedSlots = new HashMap<>();
    }

    public HashMap<Doctor, List<TimeSlot>> getBookedSlots() {
        return bookedSlots;
    }

    public void setBookedSlots(HashMap<Doctor, List<TimeSlot>> bookedSlots) {
        this.bookedSlots = bookedSlots;
    }

    public String getPatientId() {
        return id;
    }

    public String getPatientName() {
        return id;
    }
}

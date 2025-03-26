package com.problem.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.problem.utils.Sepciality;

public class Doctor extends User {

    private Sepciality specialty;
    private Set<String> availableSlots;
    private List<Booking> appointments;
    private Double rating;

    public Doctor(String id, String name, Sepciality specialty) {
        super(id, name);
        this.specialty = specialty;
        this.availableSlots = new TreeSet<>();
        this.appointments = new ArrayList<>();
        this.rating = 0.0;
    }

    public void addAvailability(String slot) {
        availableSlots.add(slot);
    }

    public void removeAvailability(String slot) {
        availableSlots.remove(slot);
    }

    public boolean isAvailable(String slot) {
        return availableSlots.contains(slot);
    }

    public void addAppointment(Booking appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Booking appointment) {
        appointments.remove(appointment);
    }

    // Getter methods
    public Sepciality getSpeciality() {
        return specialty;
    }

    public Set<String> getAvailableSlots() {
        return availableSlots;
    }

    public List<Booking> getAppointments() {
        return appointments;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}

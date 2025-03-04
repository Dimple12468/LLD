package models;

import java.util.*;

public class Patient {
    private String name;
    private List<String> bookedAppointments = new ArrayList<>();

    public Patient(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<String> getBookedAppointments() { return bookedAppointments; }

    public boolean addAppointment(String appointment) {
        if (!bookedAppointments.contains(appointment)) {
            bookedAppointments.add(appointment);
            return true;
        }
        return false;
    }

    public boolean cancelAppointment(String appointment) {
        return bookedAppointments.remove(appointment);
    }
}

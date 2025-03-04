package models;

import java.util.*;

public class Doctor {
    private String name;
    private String specialty;
    private List<String> availableSlots = new ArrayList<>();

    public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public List<String> getAvailableSlots() { return availableSlots; }

    public boolean addAvailability(String slot) {
        if (!availableSlots.contains(slot)) {
            availableSlots.add(slot);
            return true;
        }
        return false;
    }

    public boolean removeAvailability(String slot) {
        return availableSlots.remove(slot);
    }
}

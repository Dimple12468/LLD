package services;

import models.Doctor;
import java.util.*;

public class DoctorService {
    private Map<String, Doctor> doctors = new HashMap<>();

    public String registerDoctor(String name, String specialty) {
        if (doctors.containsKey(name)) {
            return "Doctor already registered.";
        }
        doctors.put(name, new Doctor(name, specialty));
        return "Welcome Dr. " + name + " !!";
    }

    public String markAvailability(String name, List<String> slots) {
        Doctor doctor = doctors.get(name);
        if (doctor == null) return "Doctor not found.";
        for (String slot : slots) {
            doctor.addAvailability(slot);
        }
        return "Done Doc!";
    }

    public List<String> getAvailabilityBySpecialty(String specialty) {
        List<String> availableSlots = new ArrayList<>();
        for (Doctor doctor : doctors.values()) {
            if (doctor.getSpecialty().equals(specialty)) {
                for (String slot : doctor.getAvailableSlots()) {
                    availableSlots.add("Dr." + doctor.getName() + ": (" + slot + ")");
                }
            }
        }
        return availableSlots;
    }
}

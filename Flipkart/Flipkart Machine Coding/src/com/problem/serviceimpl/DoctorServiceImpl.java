package com.problem.serviceimpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.problem.entity.Doctor;
import com.problem.service.DoctorService;
import com.problem.utils.Sepciality;

public class DoctorServiceImpl implements DoctorService{

    private final Map<String, Doctor> doctors = new HashMap<>();

    @Override
    public void registerDoctor(String name, Sepciality speciality) {
        if (doctors.containsKey(name)) {
            System.out.println("Doctor already registered.");
            return;
        }
        Doctor doctor = new Doctor(UUID.randomUUID().toString(), name, speciality);
        doctors.put(name, doctor);
        System.out.println("Welcome Dr. " + name + " !!");
    }

    @Override
    public void markAvailability(String doctorName, List<String> slots) {
        Doctor doctor = doctors.get(doctorName);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }
        for (String slot : slots) {
            if (!isValidSlot(slot)) {
                System.out.println("Sorry Dr. " + doctorName + " slots are 60 mins only");
                return;
            }
            doctor.addAvailability(slot);
        }
        System.out.println("Done Doc!");
    }

    @Override
    public List<Doctor> getAvailableDoctors(Sepciality speciality) {
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor doctor : doctors.values()) {
            if (doctor.getSpeciality() == speciality && !doctor.getAvailableSlots().isEmpty()) {
                availableDoctors.add(doctor);
            }
        }
        availableDoctors.sort(Comparator.comparingDouble(Doctor::getRating));
        return availableDoctors;
    }

    private boolean isValidSlot(String slot) {
        return slot.matches("\\d{1,2}:00-\\d{1,2}:00");
    }

}

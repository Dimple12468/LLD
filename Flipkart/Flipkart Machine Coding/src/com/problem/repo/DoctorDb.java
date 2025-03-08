package com.problem.repo;

import java.util.HashMap;
import java.util.Map;

import com.problem.entity.Doctor;

public class DoctorDb {

    private Map<String, Doctor> doctors = new HashMap<>();

    public void addDoctor(Doctor doctor) {
        doctors.put(doctor.getId(), doctor);
    }

    public Doctor getDoctor(String id) {
        return doctors.get(id);
    }
}

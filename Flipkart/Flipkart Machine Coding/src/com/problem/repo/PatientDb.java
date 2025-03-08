package com.problem.repo;

import java.util.HashMap;
import java.util.Map;

import com.problem.entity.Patient;

public class PatientDb {

    private Map<String, Patient> patients = new HashMap<>();

    public void addPatient(Patient patient) {
        patients.put(patient.getId(), patient);
    }

    public Patient getPatient(String id) {
        return patients.get(id);
    }

}

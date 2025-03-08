package com.problem.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.problem.entity.Patient;
import com.problem.service.PatientService;

public class PatientServiceImpl implements PatientService {

    private final Map<String, Patient> patients = new HashMap<>();

    @Override
    public void registerPatient(String name) {
        if (patients.containsKey(name)) {
            System.out.println("Patient already registered.");
            return;
        }
        Patient patient = new Patient(UUID.randomUUID().toString(), name);
        patients.put(name, patient);
        System.out.println(name + " registered successfully.");
    }

    @Override
    public Patient getPatient(String name) {
        return patients.get(name);
    }

}

package com.problem.service;

import com.problem.entity.Patient;

public interface PatientService {

    public void registerPatient(String name);

    public Patient getPatient(String name);

}

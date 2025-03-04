package services;

import models.Patient;
import java.util.*;

public class PatientService {
    private Map<String, Patient> patients = new HashMap<>();

    public Patient getPatient(String name) {
        return patients.computeIfAbsent(name, Patient::new);
    }
}

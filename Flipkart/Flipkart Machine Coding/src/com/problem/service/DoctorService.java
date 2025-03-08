package com.problem.service;

import java.util.List;

import com.problem.entity.Doctor;
import com.problem.utils.Sepciality;

public interface DoctorService {

    public void registerDoctor(String name, Sepciality speciality);

    public void markAvailability(String doctorName, List<String> slots);

    public List<Doctor> getAvailableDoctors(Sepciality speciality);

}

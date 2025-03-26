package com.problem;

import java.util.Arrays;

import com.problem.entity.Doctor;
import com.problem.entity.Patient;
import com.problem.service.BookingService;
import com.problem.service.DoctorService;
import com.problem.service.PatientService;
import com.problem.serviceimpl.BookingServiceImpl;
import com.problem.serviceimpl.DoctorServiceImpl;
import com.problem.serviceimpl.PatientServiceImpl;
import com.problem.utils.Sepciality;

public class App {
    
    public static void main(String[] args) {
        DoctorService doctorService = new DoctorServiceImpl();
        PatientService patientService = new PatientServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        // Register doctors
        doctorService.registerDoctor("Curious", Sepciality.CARDIOLOGIST);
        doctorService.markAvailability("Curious", Arrays.asList("9:00-10:00", "12:00-13:00", "16:00-17:00"));
        
        doctorService.registerDoctor("Dreadful", Sepciality.DERMATOLOGIST);
        doctorService.markAvailability("Dreadful", Arrays.asList("9:00-10:00", "11:00-12:00", "13:00-14:00"));
        
        // Show available slots by specialty
        System.out.println("Available Cardiologists:");
        for (Doctor doc : doctorService.getAvailableDoctors(Sepciality.CARDIOLOGIST)) {
            for (String slot : doc.getAvailableSlots()) {
                System.out.println("Dr." + doc.getName() + ": (" + slot + ")");
            }
        }

        // Register patients
        patientService.registerPatient("PatientA");

        // Book appointments
        Patient patientA = patientService.getPatient("PatientA");
        Doctor drCurious = doctorService.getAvailableDoctors(Sepciality.CARDIOLOGIST).get(0);
        bookingService.bookAppointment(patientA, drCurious, "12:00", false);

        // Show updated availability
        System.out.println("Updated Available Cardiologists:");
        for (Doctor doc : doctorService.getAvailableDoctors(Sepciality.CARDIOLOGIST)) {
            for (String slot : doc.getAvailableSlots()) {
                System.out.println("Dr." + doc.getName() + ": (" + slot + ")");
            }
        }

        // Cancel booking
        bookingService.cancelBooking(1001);
        
        // Show final availability
        System.out.println("Final Available Cardiologists:");
        for (Doctor doc : doctorService.getAvailableDoctors(Sepciality.CARDIOLOGIST)) {
            for (String slot : doc.getAvailableSlots()) {
                System.out.println("Dr." + doc.getName() + ": (" + slot + ")");
            }
        }
    }
}

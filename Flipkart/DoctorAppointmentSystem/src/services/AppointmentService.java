package services;

import models.*;
import java.util.*;

public class AppointmentService {
    private Map<String, Appointment> appointments = new HashMap<>();
    private Map<String, Queue<String>> waitlists = new HashMap<>();
    private DoctorService doctorService;
    private PatientService patientService;

    public AppointmentService(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public String bookAppointment(String patientName, String doctorName, String slot) {
        String key = doctorName + ":" + slot;
        if (appointments.containsKey(key)) {
            waitlists.putIfAbsent(key, new LinkedList<>());
            waitlists.get(key).offer(patientName);
            return "Slot booked. Added to waitlist.";
        }
        appointments.put(key, new Appointment(doctorName, patientName, slot));
        patientService.getPatient(patientName).addAppointment(key);
        return "Booked. Booking ID: " + key;
    }

    public String cancelAppointment(String bookingId) {
        if (!appointments.containsKey(bookingId)) return "Booking not found.";
        appointments.remove(bookingId);
        Queue<String> waitlist = waitlists.get(bookingId);
        if (waitlist != null && !waitlist.isEmpty()) {
            String nextPatient = waitlist.poll();
            appointments.put(bookingId, new Appointment(bookingId.split(":")[0], nextPatient, bookingId.split(":")[1]));
            return "Booking cancelled. Next patient from waitlist booked.";
        }
        return "Booking cancelled.";
    }
}

package com.problem.service;

import com.problem.entity.Doctor;
import com.problem.entity.Patient;

public interface BookingService {

    public void bookAppointment(Patient patient, Doctor doctor, String slot, boolean waitlist);
    
    public void cancelBooking(int bookingId);

}

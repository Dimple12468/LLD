package com.problem.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.problem.entity.Booking;
import com.problem.entity.Doctor;
import com.problem.entity.Patient;
import com.problem.entity.TimeSlot;
import com.problem.service.BookingService;

public class BookingServiceImpl implements BookingService {

    private final Map<Integer, Booking> bookings = new HashMap<>();
    private int bookingCounter = 1000;

    @Override
    public void bookAppointment(Patient patient, Doctor doctor, String slot, boolean waitlist) {
        if (!doctor.isAvailable(slot)) {
            if (waitlist) {
                System.out.println("Added to the waitlist. Booking id: " + (++bookingCounter));
            } else {
                System.out.println("Slot not available.");
            }
            return;
        }
        Booking booking = new Booking(++bookingCounter, doctor, patient, new TimeSlot(slot, slot));
        doctor.addAppointment(booking);
        patient.getBookedSlots().computeIfAbsent(doctor, k -> new ArrayList<>()).add(booking.getSlot());
        bookings.put(booking.getBookingId(), booking);
        doctor.removeAvailability(slot);
        System.out.println("Booked. Booking id: " + booking.getBookingId());
    }

    @Override
    public void cancelBooking(int bookingId) {
        Booking booking = bookings.remove(bookingId);
        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }
        booking.getDoctor().addAvailability(booking.getSlot().getStartTime());
        booking.getDoctor().removeAppointment(booking);
        booking.getPatient().getBookedSlots().get(booking.getDoctor()).remove(booking.getSlot());
        System.out.println("Booking Cancelled");
    }
}
package main;

import services.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService(doctorService, patientService);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            if (command.startsWith("registerDoc ->")) {
                String[] parts = command.split("->");
                if (parts.length == 3) {
                    String doctorName = parts[1].trim();
                    String specialty = parts[2].trim();
                    doctorService.registerDoctor(doctorName, specialty);
                    System.out.println("o: Welcome Dr. " + doctorName + " !!");
                } else {
                    System.out.println("Invalid registerDoc command format.");
                }
            } else if (command.startsWith("markDocAvail:")) {
                String[] parts = command.split(":", 2);
                if (parts.length < 2) {
                    System.out.println("Invalid markDocAvail command format.");
                    continue;
                }

                String[] details = parts[1].trim().split(" ", 2);
                if (details.length < 2) {
                    System.out.println("Invalid markDocAvail command format.");
                    continue;
                }

                String doctorName = details[0].trim();
                String[] slotArray = details[1].trim().replace(",", "").split("\\s+"); // Removes commas and splits by spaces
                List<String> slots = new ArrayList<>(Arrays.asList(slotArray));

                boolean allSlotsValid = true;
                for (String slot : slots) {
                    String[] times = slot.split("-");
                    if (times.length != 2 || !isValidSlot(times[0], times[1])) {
                        System.out.println("o: Sorry Dr. " + doctorName + " slots are 30 mins only");
                        allSlotsValid = false;
                        break;
                    }
                }
                if (allSlotsValid) {
                    doctorService.markAvailability(doctorName, slots);
                    System.out.println("o: Done Doc!");
                }
            } else if (command.startsWith("showAvailByspeciality:")) {
                String specialty = command.split(":")[1].trim();
                List<String> availableDoctors = doctorService.getAvailabilityBySpecialty(specialty);
                for (String doctorSlot : availableDoctors) {
                    System.out.println("o: " + doctorSlot);
                }
            } else if (command.startsWith("bookAppointment:")) {
                String[] parts = command.replace("bookAppointment:", "").replace("(", "").replace(")", "").split(",");
                if (parts.length == 3) {
                    String patientName = parts[0].trim();
                    String doctorName = parts[1].trim();
                    String timeSlot = parts[2].trim();
                    
                    // Generate a unique numeric ID for the booking
                    String bookingId = generateBookingId();
                    
                    appointmentService.bookAppointment(patientName, doctorName, timeSlot);
                    System.out.println("O: Booked. Booking id: " + bookingId);
                } else {
                    System.out.println("Invalid bookAppointment command format.");
                }
            } else if (command.startsWith("cancelBookingId:")) {
                String bookingId = command.split(":")[1].trim();
                appointmentService.cancelAppointment(bookingId);
                System.out.println("O: Booking Cancelled");
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }

    private static boolean isValidSlot(String start, String end) {
        try {
            String[] startTime = start.split(":");
            String[] endTime = end.split(":");

            int startHour = Integer.parseInt(startTime[0]);
            int startMinute = Integer.parseInt(startTime[1]);

            int endHour = Integer.parseInt(endTime[0]);
            int endMinute = Integer.parseInt(endTime[1]);

            return (endHour * 60 + endMinute) - (startHour * 60 + startMinute) == 30;
        } catch (Exception e) {
            return false;
        }
    }

    private static int bookingCounter = 1234; // Start booking IDs from 1234

    private static String generateBookingId() {
        return String.valueOf(bookingCounter++);
    }
}

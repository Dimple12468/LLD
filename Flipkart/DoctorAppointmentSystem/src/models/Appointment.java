package models;

public class Appointment {
    private String doctorName;
    private String patientName;
    private String slot;

    public Appointment(String doctorName, String patientName, String slot) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.slot = slot;
    }

    public String getDoctorName() { return doctorName; }
    public String getPatientName() { return patientName; }
    public String getSlot() { return slot; }
}

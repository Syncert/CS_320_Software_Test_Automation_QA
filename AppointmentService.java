package contactservice;

import java.util.HashMap;
import java.util.Map;

/*
* The appointment service shall be able to add appointments with a unique appointmentId.
* The appointment service shall be able to delete appointments per appointmentId.
*/

public class AppointmentService {
    private Map<String, Appointment> appointments;

    public AppointmentService() {
        appointments = new HashMap<>();
    }

    /**
     * Adds an appointment to the service.
     *
     * @param appointment The appointment to add.
     * @throws IllegalArgumentException If the appointment already exists.
     */
    public void addAppointment(Appointment appointment) {
        String appointmentID = appointment.getAppointmentID();
        if (appointments.containsKey(appointmentID)) {
            throw new IllegalArgumentException("Appointment with ID " + appointmentID + " already exists.");
        }
        appointments.put(appointmentID, appointment);
    }

    /**
     * Deletes an appointment from the service.
     *
     * @param appointmentID The ID of the appointment to delete.
     * @throws IllegalArgumentException If the appointment does not exist.
     */
    public void deleteAppointment(String appointmentID) {
        if (!appointments.containsKey(appointmentID)) {
            throw new IllegalArgumentException("Appointment with ID " + appointmentID + " does not exist.");
        }
        appointments.remove(appointmentID);
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentID The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     * @throws IllegalArgumentException If the appointment does not exist.
     */
    public Appointment getAppointment(String appointmentID) {
        if (!appointments.containsKey(appointmentID)) {
            throw new IllegalArgumentException("Appointment with ID " + appointmentID + " does not exist.");
        }
        return appointments.get(appointmentID);
    }
}

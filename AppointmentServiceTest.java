package contactservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/*
* The appointment service shall be able to add appointments with a unique appointmentId.
* The appointment service shall be able to delete appointments per appointmentId.
*/

public class AppointmentServiceTest {
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        appointmentService = new AppointmentService();
    }

@Test
void testAddAndRetrieveAppointment() {
    // Create a specific future date for testing
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, 1); // Add one day to the current date
    Date appointmentDate = calendar.getTime();
    
    String description = "Meeting";
    Appointment appointment = new Appointment(appointmentDate, description);

    appointmentService.addAppointment(appointment);

    Appointment retrievedAppointment = appointmentService.getAppointment(appointment.getAppointmentID());
    assertNotNull(retrievedAppointment);
    assertEquals(appointment.getAppointmentID(), retrievedAppointment.getAppointmentID());
    assertEquals(appointment.getAppointmentDate(), retrievedAppointment.getAppointmentDate());
    assertEquals(appointment.getDescription(), retrievedAppointment.getDescription());
}

    @Test
    void testDeleteAppointment() {
        Date appointmentDate = new Date();
        String description = "Meeting";
        Appointment appointment = new Appointment(appointmentDate, description);

        appointmentService.addAppointment(appointment);

        appointmentService.deleteAppointment(appointment.getAppointmentID());

        assertThrows(IllegalArgumentException.class, () -> appointmentService.getAppointment(appointment.getAppointmentID()));
    }

    @Test
    void testAddDuplicateAppointment() {
        Date appointmentDate = new Date();
        String description = "Meeting";
        Appointment appointment = new Appointment(appointmentDate, description);

        appointmentService.addAppointment(appointment);

        // Attempt to add a duplicate appointment with the same ID should throw an exception
        assertThrows(IllegalArgumentException.class, () -> appointmentService.addAppointment(appointment));
    }

    @Test
    void testDeleteNonexistentAppointment() {
        // Attempt to delete a nonexistent appointment should throw an exception
        assertThrows(IllegalArgumentException.class, () -> appointmentService.deleteAppointment("nonexistentID"));
    }
}

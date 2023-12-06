package contactservice;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.Test;

/*
 * The appointment object shall have a required unique appointment ID string that cannot be longer than 10 characters. The appointment ID shall not be null and shall not be updatable.
 * The appointment object shall have a required appointment Date field. The appointment Date field cannot be in the past. The appointment Date field shall not be null.
 * Note: Use java.util.Date for the appointmentDate field and use before(new Date()) to check if the date is in the past.
 * The appointment object shall have a required description String field that cannot be longer than 50 characters. The description field shall not be null.
 */

class AppointmentTest {

    @Test
    void testAppointmentCreationWithValidArguments() {
        // Create a date for the near future
        Date futureDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
        String description = "Valid description";

        // Try creating an appointment with valid arguments
        Appointment appointment = new Appointment(futureDate, description);

        assertNotNull(appointment.getAppointmentID());
        
        // Print the appointment ID to the console
        System.out.println("Generated Appointment ID: " + appointment.getAppointmentID());    

        assertTrue(appointment.getAppointmentID().length() <= 10);
        assertEquals(futureDate, appointment.getAppointmentDate());
        assertEquals(description, appointment.getDescription());
    }

    @Test
    void testAppointmentCreationWithPastDate() {
        // Create a date for the past
        Date pastDate = new Date(System.currentTimeMillis() - 1000 * 60 * 60);
        String description = "Valid description";

        // Attempt to create an appointment with a past date should throw an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(pastDate, description);
        });

        String expectedMessage = "Appointment date is null or in the past.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testAppointmentCreationWithNullDescription() {
        // Create a date for the near future
        Date futureDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

        // Attempt to create an appointment with a null description should throw an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(futureDate, null);
        });

        String expectedMessage = "Description is null or too long.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testAppointmentCreationWithTooLongDescription() {
        // Create a date for the near future
        Date futureDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
        String longDescription = "This description is definitely way too long to be acceptable for an appointment description.";

        // Attempt to create an appointment with a too long description should throw an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(futureDate, longDescription);
        });

        String expectedMessage = "Description is null or too long.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

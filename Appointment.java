package contactservice;
import java.util.Date;
/*
 * The appointment object shall have a required unique appointment ID string that cannot be longer than 10 characters. The appointment ID shall not be null and shall not be updatable.
 * The appointment object shall have a required appointment Date field. The appointment Date field cannot be in the past. The appointment Date field shall not be null.
 * Note: Use java.util.Date for the appointmentDate field and use before(new Date()) to check if the date is in the past.
 * The appointment object shall have a required description String field that cannot be longer than 50 characters. The description field shall not be null.
 */

public class Appointment {
    private final String appointmentID; //not updateable , not null
    private Date appointmentDate; //appointment date , not null
    private String description; //description of appointment , not null

    public Appointment(Date appointmentDate, String description) {
        // Generate a unique appointment ID, not null and not greater than 10 characters. Utility class handles uniqueness
        this.appointmentID = AppointmentIDGenerator.generateNextID();

        setAppointmentDate(appointmentDate);
        setDescription(description);
    }

    /*
     *SETTERS 
     */
    public void setAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date is null or in the past.");
        }
        this.appointmentDate = appointmentDate;
    }

    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description is null or too long.");
        }
        this.description = description;
    }


    /*
     * GETTERS
     */

    public String getAppointmentID() {
        return appointmentID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }
    
    public String getDescription() {
        return description;
    }

}

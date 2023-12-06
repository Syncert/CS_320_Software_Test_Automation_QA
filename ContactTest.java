package contactservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * REQUIREMENTS FOR CONTACT CLASS
 * The contact object shall have a required unique contact ID string that cannot be longer than 10 characters.
 * The contact ID shall not be null and shall not be updatable.
 * 
 * The contact object shall have a required firstName String field that cannot be longer than 10 characters.
 * The firstName field shall not be null.
 * 
 * The contact object shall have a required lastName String field that cannot be longer than 10 characters.
 * The lastName field shall not be null.
 * 
 * The contact object shall have a required phone String field that must be exactly 10 digits.
 * The phone field shall not be null.
 *
 * The contact object shall have a required address field that must be no longer than 30 characters.
 * The address field shall not be null.
 *
 */

public class ContactTest {

    //test Contact creation, this also tests if the generated ID is null or <= 10 from the ContactIDGenerator 
    @Test
    public void testValidContact() {
        Contact contact = new Contact("John", "Doe", "1234567890", "123 Main St");
        assertNotNull(contact);
        //test null ID
        assertNotNull(contact.getContactID());
        assertTrue(contact.getContactID().length() <= 10);
    }

 /*
  * The contact object shall have a required firstName String field that cannot be longer than 10 characters. 
  * The firstName field shall not be null.
  */

    // Test that null first name throws IllegalArgumentException
    @Test
    public void testContactWithNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "Doe", "1234567890", "123 Main St");
        });
    }

    // Test that a long first name throws IllegalArgumentException
    @Test
    public void testContactWithLongFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("JohnathanDoe", "Doe", "1234567890", "123 Main St");
        });
    }

 /*
  * The contact object shall have a required lastName String field that cannot be longer than 10 characters. 
  * The lastName field shall not be null.
  */

    // Test that null last name throws IllegalArgumentException
    @Test
    public void testContactWithNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", null, "1234567890", "123 Main St");
        });
    }

    // Test that a long last name throws IllegalArgumentException
    @Test
    public void testContactWithLongLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "DoeLongName", "1234567890", "123 Main St");
        });
    }
/*
 * The contact object shall have a required phone String field that must be exactly 10 digits. 
 * The phone field shall not be null.
 */
    // Test that null phone number throws IllegalArgumentException
    @Test
    public void testContactWithNullPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", null, "123 Main St");
        });
    }

    // Test that an incorrect phone number length over 10 throws IllegalArgumentException
    @Test
    public void testContactWithInvalidPhoneLengthLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", "12345678901", "123 Main St");
        });
    }

    // Test that an incorrect phone number length under 10 throws IllegalArgumentException
    @Test
    public void testContactWithInvalidPhoneLengthShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", "12345678", "123 Main St");
        });
    }
/*
* The contact object shall have a required address field that must be no longer than 30 characters. 
* The address field shall not be null.
*/
    // Test that null address throws IllegalArgumentException
    @Test
    public void testContactWithNullAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", "1234567890", null);
        });
    }

    // Test that a long address throws IllegalArgumentException
    @Test
    public void testContactWithLongAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("John", "Doe", "1234567890", "123 Main Street North West South East");
        });
    }


}

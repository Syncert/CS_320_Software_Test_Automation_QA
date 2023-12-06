package contactservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/*
 * The contact service shall be able to add contacts with a unique ID.
 * The contact service shall be able to delete contacts per contact ID.
 * The contact service shall be able to update contact fields per contact ID. The following fields are updatable:
 *   firstName
 *   lastName
 *   Number
 *   Address
 * 
 */

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    @Test
    public void testAddContact() {
        service.addContact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertNotNull(service.getContact("1234567890"));
    }
/*
 * The contact service shall be able to add contacts with a unique ID.
 */
    @Test
    public void testAddDuplicateContact() {
        service.addContact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact("1234567890", "Jane", "Doe", "1234567890", "456 Elm St");
        });
    }
/*
 * The contact service shall be able to delete contacts per contact ID.
 * The contact service will not try to delete non-existent contacts.
 */
    @Test
    public void testDeleteExistingContact() {
        service.addContact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        service.deleteContact("1234567890");
        assertNull(service.getContact("1234567890"));
    }

    @Test
    public void testDeleteNonExistingContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("nonExistingID");
        });
    }
/*
 * The contact service shall be able to update contact fields per contact ID. The following fields are updatable:
 */
    @Test
    public void testUpdateContact() {
        service.addContact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        service.updateContact("1234567890", "Jane", "Smith", "0987654321", "456 Elm St");
        Contact updatedContact = service.getContact("1234567890");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("Smith", updatedContact.getLastName());
        assertEquals("0987654321", updatedContact.getPhone());
        assertEquals("456 Elm St", updatedContact.getAddress());
    }
}

package contactservice;

import java.util.HashMap;
import java.util.Map;

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

public class ContactService {
    
    //create a map object with a String and Contact called contacts
    private Map<String, Contact> contacts;

    //every initialization of contactservice class create hashmap called contacts
    public ContactService() {
        contacts = new HashMap<>();
    }

    //method to add a contact ID to the contact list. 
    public void addContact(String contactID, String firstName, String lastName, String phone, String address) {
        //throw errors if the contactID is null or exists
        if (contactID == null || contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact ID is null or already exists");
        }
        Contact newContact = new Contact(firstName, lastName, phone, address);
        contacts.put(contactID, newContact);
    }

    //method to delete contact
    public void deleteContact(String contactID) {
        if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        contacts.remove(contactID);
    }
    
    // Method to update a contact
    public void updateContact(String contactID, String firstName, String lastName, String phone, String address) {
        Contact contactToUpdate = getValidContact(contactID);
        updateContactDetails(contactToUpdate, firstName, lastName, phone, address);
    }
    
    //method to verify if a contact exists
    private Contact getValidContact(String contactID) {
        if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        return contacts.get(contactID);
    }

    //update contact method
    private void updateContactDetails(Contact contact, String firstName, String lastName, String phone, String address) {
        if (firstName != null) {
            contact.setFirstName(firstName);
        }
        if (lastName != null) {
            contact.setLastName(lastName);
        }
        if (phone != null) {
            contact.setPhone(phone);
        }
        if (address != null) {
            contact.setAddress(address);
        }
    }

   // Method to retrieve a contact by contact ID
    public Contact getContact(String contactID) {
        return contacts.get(contactID);
    }

    // Method to retrieve the first name of a contact by contactID
    public String getFirstName(String contactID) {
        Contact contact = contacts.get(contactID);
        return (contact != null) ? contact.getFirstName() : null;
    }

    // Method to retrieve the last name of a contact by contactID
    public String getLastName(String contactID) {
        Contact contact = contacts.get(contactID);
        return (contact != null) ? contact.getLastName() : null;
    }

    // Method to retrieve the phone number by contactID
    public String getPhone(String contactID) {
        Contact contact = contacts.get(contactID);
        return (contact != null) ? contact.getPhone() :null;
    }
    // Method to retrieve the address by contactID
    public String getAddress(String contactID) {
        Contact contact = contacts.get(contactID);
        return (contact != null) ? contact.getAddress() : null;
    }
}
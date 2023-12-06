package contactservice;

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


public class Contact {
        private final String contactID; //final because it's not updateable 
        private String firstName;
        private String lastName;
        private String phone;
        private String address;

        public Contact(String firstName, String lastName, String phone, String address) {
            //implementing unique ID generator :) 
            this.contactID = ContactIDGenerator.generateNextID();
            
            setFirstName(firstName);
            setLastName(lastName);
            setPhone(phone);
            setAddress(address);
        }

        /*
        * SETTERS
        */

        public void setFirstName(String firstName) {
        //firstName shall not be null
        if (firstName == null) {
            throw new IllegalArgumentException("firstName is null.");
        }
        //first name shall not exceed 10 characters
        else if (firstName.length() > 10) {
            throw new IllegalArgumentException("firstName greater than 10 characters.");
        }
        this.firstName = firstName;
        }

        public void setLastName(String lastName) {
        //lastName shall not be null
        if (lastName == null) {
            throw new IllegalArgumentException("lastName is null.");
        }
        //lastName shall not exceed 10 characters
        else if (lastName.length() > 10) {
            throw new IllegalArgumentException("lastName greater than 10 characters.");
        }
        this.lastName = lastName;
        }           
        
        public void setPhone(String phone) {
        //phone shall not be null
        if (phone == null) {
            throw new IllegalArgumentException("Phone number is null.");
        }
        //phone shall be exactly 10 characters
        else if (phone.length() != 10) {
            throw new IllegalArgumentException("Phone number must be 10 characters.");
        }
        this.phone = phone;
        } 

        public void setAddress(String address) {
        //address shall not be null
        if (address == null) {
            throw new IllegalArgumentException("Address is null.");
        }
        //address shall not exceed 30 characters
        else if (address.length() > 30) {
            throw new IllegalArgumentException("Address exceeds 30 characters.");
        }
        this.address = address;
        }

        /*
        * GETTERS
        */

        public String getFirstName() {
            return firstName;
        }


        public String getLastName() {
            return lastName;
        }


        public String getContactID() {
            return contactID;
        }        

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

    }

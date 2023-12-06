package contactservice;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentIDGeneratorTest {

    @BeforeEach
    void resetIDGeneratorState() {
        AppointmentIDGenerator.reset();
    }

    //manually review generated IDs
    // @Test
    // void testGenerateAndPrintNext100IDs() {
    //     // Set a fixed date and time for testing
    //     LocalDateTime testDateTime = LocalDateTime.of(2021, Month.SEPTEMBER, 13, 9, 0); // 09:00 on the 256th day
    //     Clock fixedClock = Clock.fixed(testDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    //     AppointmentIDGenerator.setClock(fixedClock);
    
    //     AppointmentIDGenerator.reset(); // Ensure the generator is in a known state
    
    //     // Generate and print the first 100 IDs
    //     for (int i = 0; i < 250; i++) {
    //         String generatedID = AppointmentIDGenerator.generateNextID();
    //         System.out.println(generatedID);
    //     }
    // }

    @Test
    void testGenerateNextID() {
        // Set a fixed date and time for testing
        LocalDateTime testDateTime = LocalDateTime.of(2021, Month.SEPTEMBER, 13, 9, 0); // 09:00 on the 256th day
        Clock fixedClock = Clock.fixed(testDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        AppointmentIDGenerator.setClock(fixedClock);
    
        AppointmentIDGenerator.reset(); // Ensure the generator is in a known state
    
        // Generate an ID and check its format
        String generatedID = AppointmentIDGenerator.generateNextID();
        String expectedFormat = "09" + encodeBase36(255) + "AA00"; // '9' for 9th hour, base-36 encoded 255 for 256th day, 'AA' for first alphabetic sequence, '01' for first sequential number.
    
        assertEquals(expectedFormat, generatedID, "Generated ID does not match the expected format.");
    }
    
    private static String encodeBase36(int value) {
        // This logic ensures that the day of the year is correctly converted to base-36.
        // It pads the result with '0' if the result is a single digit, ensuring the day part is always two characters long.
        String base26 = Integer.toString(value, 26).toUpperCase();
        return base26.length() == 1 ? "0" + base26 : base26;
    }
    
    @Test
    void testIncrementPastMaxSequenceNumber() {
        // Set the clock to a fixed time
        LocalDateTime testDateTime = LocalDateTime.of(2021, Month.SEPTEMBER, 13, 8, 30);
        Clock fixedClock = Clock.fixed(testDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        AppointmentIDGenerator.setClock(fixedClock);
    
        // Ensure the generator is in a known state
        AppointmentIDGenerator.reset();
    
        // Run through the sequence numbers to force an increment past the maximum
        String idBeforeIncrement = "";
        for (int i = -1; i < AppointmentIDGenerator.getMaxSequenceNumber(); i++) {
            idBeforeIncrement = AppointmentIDGenerator.generateNextID();
        }
        // Generate the next ID to trigger the increment past the maximum sequence number
        String idAfterIncrement = AppointmentIDGenerator.generateNextID();
    
        // The last two digits should be '00' after the increment
        String seqNumAfterIncrement = idAfterIncrement.substring(idAfterIncrement.length() - 2);
        assertEquals("00", seqNumAfterIncrement, "The sequence number should reset to '00' after '99'");
    
        // Extract the alphabetic sequence to check if it incremented
        String alphaSeqBefore = idBeforeIncrement.substring(idBeforeIncrement.length() - 4, idBeforeIncrement.length() - 2);
        String alphaSeqAfter = idAfterIncrement.substring(idAfterIncrement.length() - 4, idAfterIncrement.length() - 2);
    
        // Convert alphabetic sequence to numbers and check for increment
        int alphaNumBefore = alphaSeqToNum(alphaSeqBefore);
        int alphaNumAfter = alphaSeqToNum(alphaSeqAfter);
    
        if (alphaSeqBefore.equals("ZZ")) {
            assertEquals(0, alphaNumAfter, "The alphabetic sequence should reset to 'AA' after 'ZZ'");
        } else {
            assertEquals(alphaNumBefore + 1, alphaNumAfter, "The alphabetic sequence should increment after sequence number reset");
        }
    }
    
    private int alphaSeqToNum(String alphaSeq) {
        int firstCharValue = alphaSeq.charAt(0) - 'A';
        int secondCharValue = alphaSeq.charAt(1) - 'A';
        return firstCharValue * 26 + secondCharValue;
    }
    

    @Test
    void alphaSeqToNumTest() {
    assertEquals(0, alphaSeqToNum("AA"), "AA should correspond to 0");
    assertEquals(1, alphaSeqToNum("AB"), "AB should correspond to 1");
    // Add more test cases as needed
}

}

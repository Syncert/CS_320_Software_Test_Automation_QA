package contactservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.*;

class ContactIDGeneratorTest {

    @BeforeEach
    void resetIDGeneratorState() {
        ContactIDGenerator.reset();
    }

    @Test
    void testGenerateNextIDFixedTime() {
        // Set a fixed date and time for testing
        LocalDateTime testDateTime = LocalDateTime.of(2021, Month.SEPTEMBER, 13, 9, 0); // 09:00 on the 256th day
        Clock fixedClock = Clock.fixed(testDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        ContactIDGenerator.setClock(fixedClock);
    
        // Generate an ID and check its format
        String generatedID = ContactIDGenerator.generateNextID();
        String expectedFormat = "2208240001"; // The expected format for the first ID at the fixed time
        
        assertEquals(expectedFormat, generatedID, "Generated ID does not match the expected format.");
    }
    

}


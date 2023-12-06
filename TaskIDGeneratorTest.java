package contactservice;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskIDGeneratorTest {

    @BeforeEach
    void resetIDGeneratorState() {
        TaskIDGenerator.reset();
    }

    @Test
    void testGenerateNextID_UniqueAndCorrectFormat() {
        // Setting a fixed date for testing
        LocalDate testDate = LocalDate.of(2021, 11, 15); // November 15, 2021
        Clock fixedClock = Clock.fixed(testDate.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        TaskIDGenerator.setClock(fixedClock);

        // Generate two IDs and assert they are unique and correctly formatted
        String firstID = TaskIDGenerator.generateNextID();
        String secondID = TaskIDGenerator.generateNextID();

        assertNotEquals(firstID, secondID, "Generated IDs should be unique.");
        assertTrue(firstID.matches("211115\\d{4}"), "First ID does not match the expected format.");
        assertTrue(secondID.matches("211115\\d{4}"), "Second ID does not match the expected format.");
    }

    @Test
    void testGenerateNextID_DayChange() {
        // Simulating the end of a day and the start of a new day
        LocalDate endOfDay = LocalDate.of(2021, 11, 15);
        LocalDate startOfNextDay = LocalDate.of(2021, 11, 16);

        Clock endOfDayClock = Clock.fixed(endOfDay.atTime(23, 59).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        TaskIDGenerator.setClock(endOfDayClock);
        String endOfDayID = TaskIDGenerator.generateNextID();

        Clock startOfNextDayClock = Clock.fixed(startOfNextDay.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        TaskIDGenerator.setClock(startOfNextDayClock);
        String startOfNextDayID = TaskIDGenerator.generateNextID();

        assertTrue(endOfDayID.startsWith("211115"), "End of day ID does not start with the correct date.");
        assertTrue(startOfNextDayID.startsWith("211116"), "Start of next day ID does not start with the correct date.");
    }
}

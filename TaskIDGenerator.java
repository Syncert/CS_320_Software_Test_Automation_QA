package contactservice;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Generates a unique Task ID.
 * Format: <YYYYMMDD><Sequential Number>
 * The date is in YYYYMMDD format, followed by a 4-digit sequential number.
 * Example: "202111150001" for the first ID generated on November 15, 2021
 * Limitations: Can generate up to 10,000 Unique IDs per day
 */

public class TaskIDGenerator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    private static int currentNumber = 0;
    private static Clock clock = Clock.systemUTC();

    public static synchronized String generateNextID() {
        LocalDate today = LocalDate.now(clock);
        currentNumber++;

        return today.format(DATE_FORMATTER) + String.format("%04d", currentNumber);
    }

    // For testing purposes
    public static void setClock(Clock testClock) {
        clock = testClock;
    }

    public static void reset() {
        currentNumber = 0;
    }
    
}



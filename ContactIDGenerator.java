package contactservice;

import java.time.Instant;
import java.time.Clock;

/**
 * Generates a unique Contact ID.
 * Format: <Unix Timestamp in seconds><Sequential Number>
 * Example: "16094592001" for the first ID generated at Unix Timestamp 1609459200
 * Unique IDs: 100 per second
 */

public class ContactIDGenerator {
    private static final long START_EPOCH = Instant.parse("2021-01-01T00:00:00Z").getEpochSecond();
    private static int currentNumber = 0;
    private static Clock clock = Clock.systemUTC();

    public static synchronized String generateNextID() {
        long timestamp = Instant.now(clock).getEpochSecond() - START_EPOCH;
        currentNumber = (currentNumber + 1) % 100; //00-99
        return String.format("%010d", timestamp * 100 + currentNumber); // Format as 10 characters
    }

    // For testing purposes
    public static void setClock(Clock testClock) {
        clock = testClock;
    }

    public static void reset() {
        currentNumber = 0;
    }

    public long getStartEpoch() {
        return START_EPOCH;
    }
}

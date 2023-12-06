package contactservice;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * Generates a unique Appointment ID.
 * Format: <Hour in Base26><DayOfYear in Base26><Alphabetic Sequence><Sequential Number>
 * Example: "9CFAA01" for an appointment at 09:00 on the 256th day of the year with sequence AA01
 * Unique IDs per day: 26 * 26 * 100 = 67,600
 */

public class AppointmentIDGenerator {
    private static final int MAX_SEQ_NUM = 99; // 00 to 99
    private static final int BASE = 26;
    private static final int MAX_ALPHA_SEQ = 26 * 26; // Two-letter combinations: AA, AB, ..., ZZ
    private static int currentAlphaSeq = 0;
    private static int currentNumber = -1; //so it increments to 0 in first ID
    private static Clock clock = Clock.systemUTC();

    public static synchronized String generateNextID() {
        LocalDateTime now = LocalDateTime.now(clock);
        int hour = now.get(ChronoField.HOUR_OF_DAY);
        int dayOfYear = now.getDayOfYear() - 1; // Base-36 encoding is zero-indexed
        
        // Check if the current number has reached the max before incrementing
        if (currentNumber >= MAX_SEQ_NUM) {
            currentNumber = 0;
            currentAlphaSeq++;
            if (currentAlphaSeq >= MAX_ALPHA_SEQ) {
                currentAlphaSeq = 0;
            }
        } else {
            currentNumber++;
        }
    
        String alphaSeq = generateAlphaSeq(currentAlphaSeq);
        return encodeBase26(hour) +
               encodeBase26(dayOfYear) +
               alphaSeq +
               String.format("%02d", currentNumber);
    }

    private static String encodeBase26(int value) {
        String base26 = Integer.toString(value, BASE).toUpperCase();
        return base26.length() == 1 ? "0" + base26 : base26;
    }
    

    private static String generateAlphaSeq(int seq) {
        char firstChar = (char) ('A' + (seq / BASE));
        char secondChar = (char) ('A' + (seq % BASE));
        return "" + firstChar + secondChar;
    }

    // For testing purposes
    public static void setClock(Clock testClock) {
        clock = testClock;
    }

    public static void reset() {
        currentAlphaSeq = 0;
        currentNumber = -1;
    }

    // Public method to get the maximum sequence number for testing purposes
    public static int getMaxSequenceNumber() {
        return MAX_SEQ_NUM;
    }

    // Public method to get the BASE 
    public static int getBase() {
        return BASE;
    }
}

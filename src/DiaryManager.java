import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Manages a  diary entries, ensuring data integrity through built-in validation.
 * <p>
 * This class represents a diary entry with an entry number, date, content, and a success status.
 * It enforces several rules upon creation and modification:
 * <ul>
 *     <li>The date must be a valid calendar date in "YYYY-MM-DD" format and not in the future.</li>
 *     <li>The entry number must be a positive integer.</li>
 *     <li>The diary entry text must not be empty or contain the '|' character.</li>
 * </ul>
 * @author stephan knappstein A01208242
 *
 * @version 1.0
 */
public class DiaryManager {
    // Instance fields
    private int entryNbr;
    private String date;
    private String diaryEntry;
    private boolean enteredSuccesfully;
    

   

    /**
     * Constructs a new DiaryManager instance with initial values.
     *
     * @param date The date of the diary entry in "YYYY-MM-DD" format.
     * @param diaryEntry The actual text content of the diary entry.
     * @param isEntered A boolean indicating if the entry was saved successfully.
     * @param entryNbr The unique number for this diary entry.
     */
    public DiaryManager(String date, String diaryEntry, boolean isEntered, int entryNbr) {
        validateDate(date); // Validate the date on creation
        this.date = date;
        validateEntryNbr(entryNbr);
        this.entryNbr = entryNbr;
        validateEntry(diaryEntry);
        this.diaryEntry = diaryEntry;
        this.enteredSuccesfully = isEntered;
    }

    /**
     * Returns the date of the diary entry.
     * @return The date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the diary entry.
     * @param date The new date.
     */
    public void setDate(String date) {
        validateDate(date); // Validate the date on update
        this.date = date;
    }

    /**
     * Checks if a string is a valid date in "YYYY-MM-DD" format, is a real
     * calendar date, and is not in the future.
     *
     * @param dateStr The date string to validate.
     * @throws IllegalArgumentException if the date is invalid.
     */
    private static void validateDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            throw new IllegalArgumentException("Date string cannot be null or empty.");
        }

        try {
            LocalDate parsedDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            if (parsedDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date cannot be in the future.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format or value. Please use YYYY-MM-DD.", e);
        }
    }

    /**
     * Validates that the entry number is a positive integer.
     *
     * @param entryNbr The entry number to validate.
     * @throws IllegalArgumentException if the entry number is not positive.
     */
    private static void validateEntryNbr(int entryNbr) {
        if (entryNbr <= 0) {
            throw new IllegalArgumentException("Entry number must be a positive integer.");
        }
    } // end of validation

    /**
     * Validates the diary entry string. Ensures it is not null, empty,
     * and does not contain the forbidden '|' character.
     *
     * @param entryString The diary entry text to validate.
     * @throws IllegalArgumentException if the entry string is invalid.
     */
    private static void validateEntry(String entryString) {
        if (entryString == null || entryString.isEmpty()) {
            throw new IllegalArgumentException("Entry string cannot be null or empty.");
        } 
        if (entryString.contains("|")) {
            throw new IllegalArgumentException("Entry string cannot contain the '|' character.");
        }
    } // end of validation
    

    /**
     * Returns the entry number of the diary entry.
     * @return The entry number.
     */
    public int getEntryNbr() {
        return entryNbr;
    }

    /**
     * Sets the entry number of the diary entry.
     * @param entryNbr The new entry number.
     */
    public void setEntryNbr(int entryNbr) {
        validateEntryNbr(entryNbr);
        this.entryNbr = entryNbr;
    }

    /**
     * Returns the content of the diary entry.
     * @return The diary entry string.
     */
    public String getDiaryEntry() {
        return diaryEntry;
    }

    /**
     * Sets the content of the diary entry.
     * @param diaryEntry The new diary entry string.
     */
    public void setDiaryEntry(String diaryEntry) {
        validateEntry(diaryEntry);
        this.diaryEntry = diaryEntry;
    }

    /**
     * Checks if the entry was successfully saved or marked as complete.
     * @return true if the entry was marked as successfully entered, false otherwise.
     */
  
    public boolean isEnteredSuccesfully() {
        return enteredSuccesfully;
    }

    /**
     * Sets the success status of the diary entry.
     * @param enteredSuccesfully True if successful, false otherwise.
     */
    public void setEnteredSuccesfully(boolean enteredSuccesfully) {
        this.enteredSuccesfully = enteredSuccesfully;
    }

    /**
     * Returns a string representation of the DiaryManager object.
     *
     * @return A string containing the values of all instance fields.
     */
    @Override
    public String toString() {
        return "DiaryManager{" +
                "entryNbr=" + entryNbr +
                ", date='" + date + '\'' +
                ", diaryEntry='" + diaryEntry + '\'' +
                ", enteredSuccesfully=" + enteredSuccesfully +
                '}';
    }
    
}

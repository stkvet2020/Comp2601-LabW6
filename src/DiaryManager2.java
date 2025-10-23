import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;


public class DiaryManager2 {
    // Instance fields
    private int entryNbr;
    private String date;
    private String diaryEntry;
    private boolean enteredSuccesfully;
    

   

    //Default Constructor
    public DiaryManager2() {
        
    }

    /**
     * Returns the date of the diary entry.
     * @return The date.
     */
    public String getDate() {
        return date;
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
     * Checks if the entry was successfully saved or marked as complete.
     * @return true if the entry was marked as successfully entered, false otherwise.
     */
  
    public boolean isEnteredSuccesfully() {
        return enteredSuccesfully;
    }

    

   

      public static void addNewEntry(){
         System.out.println("\n===Add New Entry===");
          // read file first and determine how many entries there are
          int entryCount = 0;
          try (Scanner fileScanner = new Scanner(myFile)) {
            while (fileScanner.hasNextLine()) {
              String oneline = fileScanner.nextLine();
              // You can process the line here if needed
              entryCount++;
            }
          } catch (FileNotFoundException e) {
              System.out.println("Error: Could not read the diary file.");
              e.printStackTrace();
          }
          System.out.println("Current number of entries: " + entryCount);
          // continue here populating the DiaryManager object. 
          //convert to private static methods( the methods here in main )
    }
    
}

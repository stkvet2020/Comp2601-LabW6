import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


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
      
      public static void addNewEntry(Scanner scanner, File diaryFile){
         System.out.println("\n===Add New Entry===");
          // read file first and determine how many entries there are
          int entryCount = 0;
          // This try-catch block now correctly reads the file to count existing lines.
          try (Scanner fileScanner = new Scanner(diaryFile)) {
            while (fileScanner.hasNextLine()) {
              fileScanner.nextLine(); 
              entryCount++;
            }
          } catch (FileNotFoundException e) {
              // This is not an error if the file is new. We can just proceed.
              System.out.println("Notice: Diary file not found, will start with entry #1.");
          }
          
          int newEntryNbr = entryCount + 1;
          String date;
          String diaryEntry;

          // Loop until a valid date is entered
          while (true) {
              try {
                  System.out.print("Enter date (YYYY-MM-DD): ");
                  date = scanner.nextLine();
                  validateDate(date);
                  break; // Exit loop if validation succeeds
              } catch (IllegalArgumentException e) {
                  System.out.println("Invalid input: " + e.getMessage() + " Please try again.");
              }
          }

          // Loop until a valid diary entry is entered
          while (true) {
              try {
                  System.out.print("Enter diary entry (cannot be empty or contain '|'): ");
                  diaryEntry = scanner.nextLine();
                  validateEntry(diaryEntry);
                  break; // Exit loop if validation succeeds
              } catch (IllegalArgumentException e) {
                  System.out.println("Invalid input: " + e.getMessage() + " Please try again.");
              }
          }

          // Append the new entry to the file
          try (FileWriter writer = new FileWriter(diaryFile, true)) { // 'true' for append mode
              writer.write(newEntryNbr + "|" + date + "|" + diaryEntry + "\n");
              System.out.println("Diary entry #" + newEntryNbr + " saved successfully!");
          } catch (IOException e) {
              System.out.println("An error occurred while writing to the file.");
              e.printStackTrace();
          }
    } // end of addNewEntry()
     public static void viewAllEntries(Scanner scanner, File diaryFile){
         System.out.println("\n=== All Diary Entries ===");
         boolean entriesFound = false;
        try(Scanner fileScanner = new Scanner(diaryFile)){
            while (fileScanner.hasNextLine()) {
                entriesFound = true;
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    try {
                        int entryNbr = Integer.parseInt(parts[0].trim());
                        String date = parts[1].trim();
                        String diaryEntry = parts[2].trim();
                        System.out.println("------------------------------------");
                        System.out.println("Entry #: " + entryNbr);
                        System.out.println("Date:    " + date);
                        System.out.println("Entry:   " + diaryEntry);
                    } catch (NumberFormatException e) {
                        System.out.println("------------------------------------");
                        System.out.println("Skipping corrupted line (invalid entry number): " + line);
                    }
                } else {
                    System.out.println("------------------------------------");
                    System.out.println("Skipping corrupted line (invalid format): " + line);
                }
            }
            if (!entriesFound) {
                System.out.println("No diary entries have been added yet.");
            } else {
                System.out.println("------------------------------------");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The diary file could not be found.");
        }
    } // end of viewAllEntries()
}

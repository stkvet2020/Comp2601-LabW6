import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final File myFile = new File("resources" + File.separator + "diary.txt");

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        try {
      if (myFile.createNewFile()) {           // Try to create the file
        System.out.println("File created: " + myFile.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace(); // Print error details
    }

    mainMenu(); // Start the main menu loop
    scanner.close(); // Close the scanner once the application is finished

      }// end of main method 

    public static void mainMenu() {
         boolean mainMenu = true;
         while(mainMenu){
             System.out.println("\nWelcome to  Personal Diary Management System!");
             System.out.println("==========================================");
		     System.out.println("\n===Personal Diary Management System===:");
		     System.out.println("1.Add New Entry");
		     System.out.println("2.View All Entries");
		     System.out.println("3.Search by Date");
		     System.out.println("4.Exit");
		     System.out.println("======================================");
             System.out.print("\nEnter your choice (1-4): ");
             try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline character

                if (choice == 1) {addNewEntry();}
                else if (choice == 2) {viewAllEntries();} 
                else if (choice == 3) {searchByDate();}
                else if (choice == 4) {
                    mainMenu = false;
                    System.out.println("\nThank you for using Personal Diary Management System!" + "\n Goodbye!");
                } else {
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 4.");  }
             } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from the scanner
             }

         } // end of while loop 
           
    
    } // end of MainMenu()
     
         public static void viewAllEntries(){
         System.out.println("\nInside viewAllEntries() method");}

         public static void searchByDate(){
         System.out.println("\nInside searchByDate() method");}

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
    } // end of class 

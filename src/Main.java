import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main entry point for the Personal Diary Management System application.
 * This class is responsible for initializing the application, creating the diary file if it doesn't exist,
 * and running the main user menu loop.
 *
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final File myFile = new File("resources" + File.separator + "diary.txt");

    /**
     * The main method that starts the application.
     * It ensures the diary file exists and then launches the main menu.
     *
     * @param args Command line arguments (not used).
     */
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

    /**
     * Displays the main menu and handles user navigation.
     * This method runs a loop that prompts the user for a choice (1-4) and calls the
     * corresponding static methods from the {@link DiaryManager2} class to perform actions.
     */
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

                if (choice == 1) {DiaryManager2.addNewEntry(scanner, myFile);}
                else if (choice == 2) {DiaryManager2.viewAllEntries(scanner,myFile);} 
                else if (choice == 3) {DiaryManager2.searchByDate(scanner,myFile);}
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
     
         

        

       
    } // end of class 

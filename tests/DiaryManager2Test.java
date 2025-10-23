import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the DiaryManager2 class.
 */
class DiaryManager2Test {

    // JUnit will create a temporary directory for us before each test
    @TempDir
    Path tempDir;

    @Test
    void addNewEntry_shouldWriteCorrectlyFormattedEntryToFile() throws IOException {
        // Arrange: Simulate user input and prepare a temporary file
        String simulatedInput = "2023-10-27\nThis is a test entry.\n";
        Scanner scanner = new Scanner(simulatedInput);
        File diaryFile = tempDir.resolve("diary.txt").toFile();

        // Act: Call the method to be tested
        DiaryManager2.addNewEntry(scanner, diaryFile);

        // Assert: Check the file content
        List<String> lines = Files.readAllLines(diaryFile.toPath());
        assertEquals(1, lines.size());
        assertEquals("1|2023-10-27|This is a test entry.", lines.get(0));
    }

    @Test
    void addNewEntry_shouldRejectInvalidDateThenAcceptValidDate() throws IOException {
        // Arrange: Simulate an invalid date, then a valid one
        String simulatedInput = "2023/10/27\n2023-10-27\nAnother test.\n";
        Scanner scanner = new Scanner(simulatedInput);
        File diaryFile = tempDir.resolve("diary.txt").toFile();

        // Act
        DiaryManager2.addNewEntry(scanner, diaryFile);

        // Assert
        List<String> lines = Files.readAllLines(diaryFile.toPath());
        assertEquals("1|2023-10-27|Another test.", lines.get(0));
    }

}



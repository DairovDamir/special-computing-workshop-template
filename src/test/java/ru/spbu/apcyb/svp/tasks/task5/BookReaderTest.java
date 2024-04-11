package ru.spbu.apcyb.svp.tasks.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task3.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BookReaderTest {

    private BookReader bookReader;
    private Path pathOfTheBook;
    private Path pathOfTheCounts;

    @BeforeEach
    void setUp() {
        pathOfTheBook = Path.of("src/main/resources/Book.txt");
        pathOfTheCounts = Path.of("src/main/resources/counts");
        bookReader = new BookReader(pathOfTheBook, pathOfTheCounts);
    }

    @Test
    void readBookThrowsRuntimeException() {
        bookReader.setPathOfTheBook(Path.of("Do not Exist"));
        Assertions.assertThrows(NoSuchFileException.class, () -> bookReader.readBook());
    }

    @Test
    void readBook() throws IOException {
        bookReader.setPathOfTheBook(Path.of("src/main/resources/BookFileForTest.txt"));

        Map<String, Integer> expectedFromCounts = new HashMap<>();
        expectedFromCounts.put("Book", 1);
        expectedFromCounts.put("for", 2);
        expectedFromCounts.put("testing", 2);

        bookReader.readBook();
        Map<String, Integer> counts = new HashMap();
        BufferedReader bufferedReader = Files.newBufferedReader(pathOfTheCounts);

        while (bufferedReader.ready()) {
            String[] line = bufferedReader.readLine().split(" ");
            counts.put(line[0], Integer.parseInt(line[1]));
        }

        Assertions.assertEquals(expectedFromCounts, counts);
    }

    @Test
    void makeFiles() throws IOException {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Enter to Directory: src/main/java/ru/spbu/apcyb/svp/tasks/task5/DirectoryForTesting");
        expectedList.add("File name: src/main/java/ru/spbu/apcyb/svp/tasks/task5/DirectoryForTesting/for.txt");
        expectedList.add("File name: src/main/java/ru/spbu/apcyb/svp/tasks/task5/DirectoryForTesting/testing.txt");
        expectedList.add("File name: src/main/java/ru/spbu/apcyb/svp/tasks/task5/DirectoryForTesting/Book.txt");
        expectedList.add("Exit from Directory: src/main/java/ru/spbu/apcyb/svp/tasks/task5/DirectoryForTesting");

        bookReader.setPathOfTheBook(Path.of("src/main/resources/BookFileForTest.txt"));
        bookReader.setPathOfTheDirectory(Path.of("src/main/java/ru/spbu/apcyb/svp/tasks/task5/DirectoryForTesting"));

        bookReader.readBook();
        bookReader.makeFiles();

        Tree tree = new Tree(Path.of("src/main/resources/WriterOfTheDirectoryFileForTest"),
                Path.of("src/main/java/ru/spbu/apcyb/svp/tasks/task5/DirectoryForTesting"));

        tree.walker();

        List<String> listOfTheFilesThatWereMake = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(Path.of("src/main/resources/WriterOfTheDirectoryFileForTest"));
        while (bufferedReader.ready()) {
            listOfTheFilesThatWereMake.add(bufferedReader.readLine());
        }

        Assertions.assertEquals(expectedList, listOfTheFilesThatWereMake);
    }
}
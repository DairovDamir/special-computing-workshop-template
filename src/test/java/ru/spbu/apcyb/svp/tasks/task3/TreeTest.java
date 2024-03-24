package ru.spbu.apcyb.svp.tasks.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TreeTest {

    private Tree tree;

    @BeforeEach
    void setUp() {
        tree = new Tree(Path.of("src/main/resources/WriterFile"),
                Path.of("src/main/java/ru/spbu/apcyb/svp/tasks/task3"));
    }

    @Test
    void walkerIfFileIsNotExist() {
        tree.setPathOfTheWriterFile(Path.of("/FileIsNotExist"));
        assertThrows(FileNotFoundException.class, () -> tree.walker());
    }

    @Test
    void walker() {
        ArrayList<String> files = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(Path.of("/Users/damir/IdeaProjects/special-computing-workshop-template/src/main/resources/WriterFile"))) {
            tree.walker();
            while (br.ready()) {
                files.add(br.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> expected = new ArrayList<>();

        expected.add("Enter to Directory: src/main/java/ru/spbu/apcyb/svp/tasks/task3");
        expected.add("File name: src/main/java/ru/spbu/apcyb/svp/tasks/task3/Tree.java");
        expected.add("File name: src/main/java/ru/spbu/apcyb/svp/tasks/task3/MyFileVisitor.java");
        expected.add("Exit from Directory: src/main/java/ru/spbu/apcyb/svp/tasks/task3");

        assertEquals(expected, files);
    }

    @Test
    void walkerIfThePathToDirectoryIsNotCorrect() {
        tree = new Tree(Path.of("src/main/resources/WriterFile"), Path.of(" "));
        assertThrows(IOException.class, () -> tree.walker());
    }

    @Test
    void walkerIfThePathToDirectoryIsAPathToFile() {
        tree = new Tree(Path.of("src/main/resources/WriterFile"), Path.of("src/main/resources/WriterFile"));
        assertThrows(IOException.class, () -> tree.walker());
    }

    @Test
    void mainIfArgsIsEmpty() {
        String[] args = new String[0];
        assertThrows(IOException.class, () -> Tree.main(args));
    }
}
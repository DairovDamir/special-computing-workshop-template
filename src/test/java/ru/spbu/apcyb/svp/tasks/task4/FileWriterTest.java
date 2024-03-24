package ru.spbu.apcyb.svp.tasks.task4;

import java.io.IOException;
import java.nio.file.*;
import java.io.BufferedReader;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FileWriterTest {
    private FileWriter fileWriter;
    private final Path path = Path.of("./src/main/resources/Writer");

    @BeforeEach
    void setUp() throws IOException {
        fileWriter = new FileWriter(path, 10);
    }

    @Test
    void getArgumentsIfIndexIsOutOfBound() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> fileWriter.getArguments(11));
    }

    @Test
    void getArgumentsIfIndexIsCorrect() {
        double[] excepted = new double[10];
        int i = 0;
        try(BufferedReader br = Files.newBufferedReader(path)) {
            while (br.ready()) {
                String str = br.readLine();
                excepted[i] = (Double.parseDouble(str));
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(excepted[5], fileWriter.getArguments(5));
    }

    @Test
    void getNumberOfArguments() {
        Assertions.assertEquals(10, fileWriter.getNumbersOfArguments());
    }

    @Test
    void writeTanMultiThreadingValues() throws IOException, InterruptedException {
        String[] string = new String[11];
        double[] expected = new double[10];
        int i = 0;

        fileWriter.calculate(10, fileWriter);
        Path pathOfTanValueFile = Path.of("src/main/resources/TanValue");
        fileWriter.writeTanMultiThreadingValues(pathOfTanValueFile);

        try (BufferedReader br = Files.newBufferedReader(pathOfTanValueFile)){
            while (br.ready()) {
                String str = br.readLine();
                string[i] = str;
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        i = 0;
        try (BufferedReader br = Files.newBufferedReader(path)){
            while (br.ready()) {
                String str = br.readLine();
                expected[i] = Math.tan(Double.parseDouble(str));
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        i = 0;
        String[] expectedInString = new String[11];
        for (; i < 10;) {
            expectedInString[i] = String.valueOf(expected[i]);
            i++;
        }
        expectedInString[10] = "count = 10";

        Assertions.assertArrayEquals(expectedInString, string);
    }


}
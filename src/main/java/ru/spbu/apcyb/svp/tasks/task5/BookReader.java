package ru.spbu.apcyb.svp.tasks.task5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookReader {

    private Path pathOfTheBook;
    private final Path pathOfTheCounts;
    private Map<String, Long> words;
    private Path pathOfTheDirectory;

    public BookReader(Path pathOfTheBook, Path pathOfTheCounts) {
        this.pathOfTheBook = pathOfTheBook;
        this.pathOfTheCounts = pathOfTheCounts;
    }

    public void setPathOfTheDirectory(Path pathOfTheDirectoryToWrite) {
        this.pathOfTheDirectory = pathOfTheDirectoryToWrite;
    }

    public void setPathOfTheBook(Path pathOfTheBook) {
        this.pathOfTheBook = pathOfTheBook;
    }

    public void readBook() throws IOException {
        try (Stream<String> text = Files.lines(pathOfTheBook)) {
            words = text.map(word -> word.replaceAll("[,.?!—:;«»]", ""))
                    .flatMap(word -> Stream.of(word.split(" ")))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            writeInCounts();
        } catch (IOException e) {
            throw new NoSuchFileException("Данная книга не найдена");
        }
    }

    private void writeInCounts() throws IOException {
        Files.write(pathOfTheCounts,
                words.entrySet().stream()
                        .map(k -> k.getKey() + " " + k.getValue()).collect(Collectors.toList()));
    }

    public void makeFiles() {
        words.forEach((word, num) -> CompletableFuture.runAsync(() -> {
            Path path = Path.of(String.valueOf(pathOfTheDirectory), word + ".txt");
            try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path)){
                for (int i  = 0; i < num; i++) {
                    bufferedWriter.write(word + " ");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

    }


    public static void main(String[] args) throws IOException {
        Path pathOfTheBook = Path.of("src/main/resources/Book.txt");
        Path pathOfTheCounts = Path.of("src/main/resources/counts");

        BookReader bookReader = new BookReader(pathOfTheBook, pathOfTheCounts);

        bookReader.readBook();
        bookReader.makeFiles();
    }
}
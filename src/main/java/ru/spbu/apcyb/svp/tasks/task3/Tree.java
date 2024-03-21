package ru.spbu.apcyb.svp.tasks.task3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Tree {
    private Path pathOfTheWriterFile;
    private Path pathOfTheDirectory;

    public Tree(Path path, Path path2) {
        this.pathOfTheWriterFile = path;
        this.pathOfTheDirectory = path2;
    }

    public void walker() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(pathOfTheWriterFile)) {
            File file = pathOfTheDirectory.toFile();
            if (!file.isDirectory()) {
                throw new IOException();
            }
            Files.walkFileTree(pathOfTheDirectory, new MyFileVisitor(writer));
        } catch (IOException e) {
            throw new FileNotFoundException("The path to the directory is the path to a file or not correct");
        }
    }

    public void setPathOfTheWriterFile(Path pathOfTheWriterFile) {
        this.pathOfTheWriterFile = pathOfTheWriterFile;
    }

    public void setPathOfTheDirectory(Path pathOfTheDirectory) {
        this.pathOfTheDirectory = pathOfTheDirectory;
    }

    public Path getPathOfTheWriterFile() {
        return pathOfTheWriterFile;
    }

    public Path getPathOfTheDirectory() {
        return pathOfTheDirectory;
    }

    public static void main(String[] args) throws IOException {

        boolean correctSize = Objects.equals(2, args.length);
        if (!correctSize) {
            throw new IOException("2 arguments must be passed in main");
        }
        Path pathOfTheWriterFile = Path.of(args[0]);
        Path pathOfTheDirectory = Path.of(args[1]);


        Tree tree = new Tree(pathOfTheWriterFile, pathOfTheDirectory);
        tree.walker();

    }
}

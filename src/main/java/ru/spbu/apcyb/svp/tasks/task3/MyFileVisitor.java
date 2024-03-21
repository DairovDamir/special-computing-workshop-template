package ru.spbu.apcyb.svp.tasks.task3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor implements FileVisitor<Path>{
    private final BufferedWriter bw;

    public MyFileVisitor(BufferedWriter bw) throws IOException {
        this.bw = bw;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {
        bw.write("Enter to Directory: " + dir.toString());
        bw.newLine();
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        bw.write("File name: " + file.toString());
        bw.newLine();
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc)
            throws IOException {
        bw.write("Error when visiting file: " + file.toString());
        bw.newLine();
        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {
        bw.write("Exit from Directory: " + dir.toString());
        bw.newLine();
        return FileVisitResult.CONTINUE;
    }
}

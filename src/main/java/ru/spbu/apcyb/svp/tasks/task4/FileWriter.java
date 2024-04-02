package ru.spbu.apcyb.svp.tasks.task4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileWriter {

    private final double[] arguments;
    private final List<Double> tanInMultiThreading = Collections.synchronizedList(new ArrayList<>());

    private final List<Double> tanInSingleThreading = new ArrayList<>();

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public FileWriter(Path path, int numOfArguments) throws IOException{
        arguments = new double[numOfArguments];
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (int i = 0; i < numOfArguments; i++) {
                arguments[i] = Math.random();
                bufferedWriter.write(String.valueOf(arguments[i]));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {throw new NoSuchFileException("File does not exist");}
    }

    public double getArguments(int i) {
        return arguments[i];
    }

    public int getNumbersOfArguments() {
        return arguments.length;
    }

    public  void setTanInMultiThreading(double num) {
        tanInMultiThreading.add(num);
    }

    public void writeTanMultiThreadingValues(Path path1) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path1)) {
            for (int i = 0; i < tanInMultiThreading.size(); i++) {
                bufferedWriter.write(String.valueOf(tanInMultiThreading.get(i)));
                bufferedWriter.newLine();
            }
            bufferedWriter.write("count = " + tanInMultiThreading.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeTanSingleThreadedValues(Path path1) {
        for (int i = 0; i < arguments.length; i++) {
            tanInSingleThreading.add(Math.tan(arguments[i]));
        }

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path1)) {
            for (int i = 0; i < tanInSingleThreading.size(); i++) {
                bufferedWriter.write(String.valueOf(tanInSingleThreading.get(i)));
                bufferedWriter.newLine();
            }
            bufferedWriter.write("count = " + tanInSingleThreading.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void calc(FileWriter fileWriter) {
        executorService.execute(new Multithreading(arguments.length, fileWriter));
        executorService.shutdown();
    }
}

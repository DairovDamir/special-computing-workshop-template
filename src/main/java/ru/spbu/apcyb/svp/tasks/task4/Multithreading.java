package ru.spbu.apcyb.svp.tasks.task4;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Multithreading implements Runnable{
    private final int startNum;
    private final int finishNum;
    private final FileWriter fileWriter;

    public Multithreading(int startNum, int finishNum, FileWriter fileWriter) {
        this.startNum = startNum;
        this.finishNum = finishNum;
        this.fileWriter = fileWriter;
    }
    @Override
    public void run() {
            for (int i = startNum; i <= finishNum; i++) {
                fileWriter.setTanInMultiThreading(Math.tan(fileWriter.getArguments(i)));
                System.out.println(i);
            }
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        int num = 0;
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();

        Path path = Path.of(args[0]);
        FileWriter fileWriter = new FileWriter(path, num);


        double startOfWork = System.currentTimeMillis();
        fileWriter.calculate(num, fileWriter);
        fileWriter.writeTanMultiThreadingValues(Path.of(args[1]));
        double finishOfWork = System.currentTimeMillis();

        double timeOfWorkInMultiThreading = finishOfWork - startOfWork;

        startOfWork = System.currentTimeMillis();
        fileWriter.writeTanSingleThreadedValues(Path.of(args[1]));
        finishOfWork = System.currentTimeMillis();

        double timeOfWorkInSingleThreading = finishOfWork - startOfWork;

        System.out.print("Time in Mult: " + timeOfWorkInMultiThreading +
                "\n" + "Time in Single: " + timeOfWorkInSingleThreading);

    }
}


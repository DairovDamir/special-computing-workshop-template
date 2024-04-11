package ru.spbu.apcyb.svp.tasks.task1;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Atm {
    private static Long  sum;
    private static final List<Long> banknotes = new ArrayList<>();
    private int count = 0;
    private List<long[]> comb = new ArrayList<>();
    private final Logger LOGGER = Logger.getLogger(Atm.class.getName());
    private final ConsoleHandler ch = new ConsoleHandler();
    private final SimpleFormatter sf = new SimpleFormatter();
    public static void scan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount and denominations of the bills separated by a space:");
        String string = scanner.nextLine();
        String[] str = string.split(" ");

        sum = Long.parseLong(str[0]);
        for (int i = 1; i < str.length; i++) {
            banknotes.add(Long.parseLong(str[i]));
        }
        HashSet<Long> set = new HashSet<>(banknotes);
        banknotes.clear();
        banknotes.addAll(set);
        Collections.reverse(banknotes);
    }

    public static Long getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

    public void getComb() {
        calculateComb(sum, new long[banknotes.size()], 0);
        LOGGER.log(Level.INFO, "count : {0} ", count);
    }
    public void printBanknotes() {
        for(Long l : banknotes) {System.out.print(l + " ");}
    }

    private void calculateComb(long current, long[] nominal, int index) {

        long coinQuantity = sum / banknotes.get(index);

        for (int i = 0; i <= coinQuantity; i++) {
            if (current >= 0) {
                nominal[index] = i;

                if (current == 0) {
                    count++;
                    for (int j = 0; j < nominal.length; j++) {
                        for (int k = 0; k < nominal[j]; k++) {
                            LOGGER.log(Level.INFO, "{0}", banknotes.get(j));
                        }
                    }
                    LOGGER.info("End of the comb");

                    if (count < 1000) {
                        comb.add(nominal);
                    }
                } else if (index + 1 < banknotes.size()) {
                    calculateComb(current, nominal.clone(), index + 1);
                }
            }
            current -= banknotes.get(index);
        }
    }



    public static void main(String[] args) {
        Atm a = new Atm();
        a.scan();
        a.getComb();
    }

}

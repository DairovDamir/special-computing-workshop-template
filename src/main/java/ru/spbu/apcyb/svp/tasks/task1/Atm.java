package ru.spbu.apcyb.svp.tasks.task1;

import java.util.*;
import java.util.logging.*;

public class Atm {
    private static Long sum;
    private static final List<Long> banknotes = new ArrayList<>();
    private int count = 0;
    private List<long[]> comb = new ArrayList<>();
    private final Logger LOGGER = Logger.getLogger(Atm.class.getName());

    public static void scan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount and denominations of the bills separated by a space:");
        String string = scanner.nextLine();
        String[] str = string.split(" ");

        if(!isNum(str[0])) {
            throw new NumberFormatException("The value of the amount is not a number");
        }
        sum = Long.parseLong(str[0]);
        for (int i = 1; i < str.length; i++) {
            if (!isNum((str[i]))) {
                throw new NumberFormatException("The value of a banknote is not a number");
            }
            banknotes.add(Long.parseLong(str[i]));
        }
        HashSet<Long> set = new HashSet<>(banknotes);
        banknotes.clear();
        banknotes.addAll(set);
        Collections.reverse(banknotes);
    }

    public static void scan(String string) {
        String[] str = string.split(" ");

        if(!isNum(str[0])) {
            throw new NumberFormatException("The value of the amount is not a number");
        }
        sum = Long.parseLong(str[0]);
        for (int i = 1; i < str.length; i++) {
            if (!isNum((str[i]))) {
                throw new NumberFormatException("The value of a banknote is not a number");
            }
            banknotes.add(Long.parseLong(str[i]));
        }
        HashSet<Long> set = new HashSet<>(banknotes);
        banknotes.clear();
        banknotes.addAll(set);
        Collections.reverse(banknotes);
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public void setBanknotes(String banknotes1) {
        String[] b = banknotes1.split(" ");
        for (String s : b) {
            banknotes.add(Long.parseLong(s));
        }
    }

    public static Long getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

    public void getComb() throws Exception {
        if (sum < 0) {
            throw new Exception("The value of the amount is less than zero");
        } else if (sum < banknotes.get(banknotes.size() - 1)) {
            throw new Exception("The value of the amount is less than the value of the smallest banknote");
        } else {
            calculateComb(sum, new long[banknotes.size()], 0);
            LOGGER.log(Level.INFO, "count : {0} ", count);
        }
    }
    public void printBanknotes() {
        for(Long l : banknotes) {System.out.print(l + " ");}
    }

    public List<long[]> getAllCombWithList() {
        return comb;
    }

    private void calculateComb(long current, long[] nominal, int index) {

        long coinQuantity = sum / banknotes.get(index);

        for (int i = 0; i <= coinQuantity; i++) {
            if (current >= 0) {
                nominal[index] = i;

                if (current == 0) {
                    count++;
                    List<String> printList = new ArrayList<>();
                    for (int j = 0; j < nominal.length; j++) {
                        for (int k = 0; k < nominal[j]; k++) {
                            printList.add(String.valueOf(banknotes.get(j)));
                        }
                    }
                    LOGGER.log(Level.INFO, "{0}", printList);

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

    private static boolean isNum(String str) {
        if (str == null) {
            return false;
        }

        try {
            Long l = Long.parseLong(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }



    public static void main(String[] args) throws Exception{
        Atm a = new Atm();
        a.scan();
        a.getComb();

    }

}

package tool_input;

import java.util.ArrayList;
import java.util.Scanner;

public class GetInput {
    static Scanner sc = new Scanner(System.in);

    // get String input and check blank
    public static String getString(String mess) {
        boolean flag = true;
        String result;
        do {
            System.out.print(mess);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(" -> This information can not be blank! ~ Pls re-enter.....");
            } else {
                flag = false;
            }
        } while (flag);
        return result;
    }

    // get string input and get old data if blank
    public static String getNewString(String mess, String oldData) {
        System.out.print(mess);
        String result = sc.nextLine();
        if (result.isEmpty()) {
            result = oldData;
        }
        return result;
    }

    // get integer input and check min with max
    public static int getInt(String mess, int min, int max) {
        boolean flag = true;
        int number = 0;
        do {
            try {
                System.out.print(mess);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println(" => The number must greater than " + min);
                } else if (number > max) {
                    System.out.println(" => The number must smaller than " + max);
                } else {
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println(" -> You entered the wrong format ~ Pls re-enter...");
            }
        } while (flag);
        return number;
    }

    // get integer input and if it blank -> return old data
    public static int getNewInt(String mess, int min, int max, int oldData) {
        boolean flag = true;
        int number = oldData;
        do {
            try {
                System.out.print(mess);
                String tmp = sc.nextLine();
                if (!tmp.isEmpty()) {
                    number = Integer.parseInt(tmp);
                    flag = false;
                } else if (number < min) {
                    System.out.println(" => The number must greater than " + min);
                } else if (number > max) {
                    System.out.println(" => The number must smaller than " + max);
                } else {
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println(" -> you entered the wrong format! ~ Pls re-enter...");
            }
        } while (flag);
        return number;
    }

    // get String option for input
    public static String getOption(String mess, ArrayList<String> listOp, boolean flag, String oldData) {
        do {
            System.out.println(mess);
            for (int i = 0; i < listOp.size(); i++) {
                System.out.println(i + 1 + ". " + listOp.get(i));
            }
            System.out.print("=> ");
            String choice = sc.nextLine();
            for (int i = 0; i < listOp.size(); i++) {
                try {
                    if (choice.equalsIgnoreCase(listOp.get(i)) || choice.equalsIgnoreCase(String.valueOf(i + 1))) {
                        return listOp.get(i);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" => Pls enter a number or a string!");
                }
            }
            if (flag) {
                return oldData;
            } else {
                System.out.println(" => Pls choose one of options above!");
            }
        } while (true);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Utilities;

import BussinessLayer.Entity.Product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author NguyenDuy
 */
public class DataInput{

    private final Scanner sc;

    public DataInput() {
        sc = new Scanner(System.in);
    }
    
    public int inputInteger(String msg, int x, int y) {
        System.out.println(msg);
        boolean check = true;
        int input;
        try {
            while (check) {
                input = Integer.parseInt(sc.nextLine());
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + " to " + y);
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("This must be number");
            check = true;
        }
        return 0;
    }
  
    public double inputDouble(String msg, int x, int y) {
        System.out.println(msg);
        boolean check = true;
        double input;
        try {
            while (check) {
                input = Double.parseDouble(sc.nextLine());
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + "to" + y);
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("This must be number");
            check = true;
        }
        return 0;
    }
    
    
    
    public String inputStringNotBlank(String msg) {
        String input = "";
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        do {
            System.out.println(msg);
            input = sc.nextLine();
        } while (input.trim().isEmpty() || !pattern.matcher(input).matches());
        return input;
    }
    
    
    
    public boolean inputYN(String msg) {
        String choice;
        while (true) {
            System.out.println(msg);
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                return true;
            } else if (choice.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must be Y or N");
                continue;
            }
        }
    }
 
    public LocalDate inputDate(String msg, String pattern) {
        System.out.println(msg);
        boolean check = true;
        LocalDate date = null;
        while (check) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                date = LocalDate.parse(sc.nextLine(), dtf);
                check = false;
            } catch (DateTimeParseException e) {
                System.out.println("Format date must be " + pattern);
                check = true;
            }
        }
        return date;
    }
  
    public LocalDate inputDateUD(String msg, String pattern, Product product, int x) {
        System.out.println(msg);
        boolean check = true;
        LocalDate date = null;
        while (check) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                String input = sc.nextLine();
                if(input.trim().isEmpty()){
                    if(x == 1){
                        date = product.getManufacturingDate();
                        return date;
                    }else{
                        date = product.getExpirationDate();
                        return date;
                    }
                }
                date = LocalDate.parse(input, dtf);
                check = false;
            } catch (DateTimeParseException e) {
                    System.out.println("Format date must be " + pattern);
                    check = true;
            }
        }
        return date;
    }
}

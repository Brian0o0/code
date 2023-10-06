/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Components;

import Application.Utilities.DataInput;
import BussinessLayer.Entity.Product;
import BussinessLayer.Entity.WarehouseExport;
import BussinessLayer.Entity.WarehouseImport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 *
 * @author NguyenDuy
 */
public class DataValidation {

    private final Scanner sc;
    private final SearchData sd;
    private final DataInput di;

    public DataValidation() {
        sc = new Scanner(System.in);
        sd = new SearchData();
        di = new DataInput();
    }

    public String inputProductID(ArrayList<Product> arr) {
        String id;
        System.out.println("Enter product id: ");
        Pattern pattern = Pattern.compile("^\\S*$");
        do {
            id = sc.nextLine();
            if (sd.searchProductByID(arr, id) != null) {
                System.err.println("Duplicated code.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.err.println("ID can't not empty!");
            } else if (!pattern.matcher(id).matches()) {
                System.out.println("Id must not have spaces");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }

    public String createWarehouseExportID(ArrayList<WarehouseExport> arr) {
        String id;
        int countElement = arr.size();
        if (countElement == 0) {
            return "E0000001";
        } else {
            int index = arr.size() - 1;
            String getID = arr.get(index).getExportID();
            StringTokenizer stk = new StringTokenizer(getID, "E");
            int lastNum = Integer.parseInt(stk.nextToken());
            id = String.format("E%07d", lastNum + 1);
        }
        return id;
    }

    public String createWarehouseImportID(ArrayList<WarehouseImport> arr) {
        String id;
        int countElement = arr.size();
        if (countElement == 0) {
            return "I0000001";
        } else {
            int index = arr.size() - 1;
            String getID = arr.get(index).getImportID();
            StringTokenizer stk = new StringTokenizer(getID, "I");
            int lastNum = Integer.parseInt(stk.nextToken());
            id = String.format("I%07d", lastNum + 1);
        }
        return id;
    }

    public int ValidManufacturingExpirationDate(LocalDate expirationDate, LocalDate manufacturingDate) {
        int choice = 0;
        System.out.println("Expiration time can't before manufacturing time");
        System.out.println("What do you want to fix?");
        System.out.println("1. Manufacturing Date");
        System.out.println("2. Expiration Date");
        choice = di.inputInteger("Choice 1 or 2", 1, 2);
        return choice;
    }

    public int inputQuantityDB(String msg, int x, int y, Product product) {
        System.out.println(msg);
        boolean check = true;
        int input;
        try {
            while (check) {
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    input = product.getQuantity();
                    return input;
                }
                input = Integer.parseInt(string);
                if (input < x || input > y) {
                    System.out.println("This number must be " + (x + 1) + "to" + (y - 1));
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

    public double inputPriceDB(String msg, int x, int y, Product product) {
        System.out.println(msg);
        boolean check = true;
        double input;
        try {
            while (check) {
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    input = product.getPrice();
                    return input;
                }
                input = Double.parseDouble(string);
                if (input < x || input > y) {
                    System.out.println("This number must be " + (x + 1) + "to" + (y - 1));
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

    public String inputNameUpdate(String msg, Product product) {
        String input = "";
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        do {
            System.out.println(msg);
            input = sc.nextLine();
            if (input.trim().isEmpty()) {
                input = product.getNameProduct();
            }
        } while (!pattern.matcher(input).matches());
        return input;
    }
}

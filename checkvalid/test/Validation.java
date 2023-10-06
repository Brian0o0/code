/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ASUS
 */
import checkvalid.ICheckValid;
import java.util.List;
import entity.Product;
import entity.WareHouse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Validation implements ICheckValid{

    private Scanner sc = new Scanner(System.in);
    
    @Override
    public String checkString(String msg, String status) {
        // vong lap su dung de nguoi dung nhap den khi dung 
        while (true) {
            System.out.println(msg);
            // allow user input a string 
            String input_raw = sc.nextLine();
            if(status.equals("update") && input_raw.isEmpty()){
                return input_raw;
            }
            // input == null or do dai = 0 => rong 
            if (input_raw == null || input_raw.length() == 0) {
                // error
                System.err.println("Must input a string not empty !!!");
                System.out.println("Please enter again!");
                continue;
            }
            return input_raw;
        }
    }

    @Override
    public String checkProductCodeExist(String msg, List<Product> listProduct, String status) {
        while (true) {
            // khai bao bien co de kiem tra xem co trung hay khong, neu trung thi flag = 1 
            int flag = 0;
            // NHAP ID DE CHECK 
            String id = checkString(msg,status);
            
            for (Product item : listProduct) {
                if (item.getpCode().equals(id)) {
                    System.err.println("Id existed!!Please enter again");
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                continue;
            }
            return id;
        }
    }

    @Override
    public String checkReceiptCodeExist(String msg, List<WareHouse> listWareHouse) {
        while (true) {
            // khai bao bien co de kiem tra xem co trung hay khong, neu trung thi flag = 1 
            int flag = 0;
            // NHAP ID DE CHECK 
            String id = checkString(msg,null);
            
            for (WareHouse item : listWareHouse) {
                if (item.getCode().equals(id)) {
                    System.err.println("Receipt code existed!!Please enter again");
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                continue;
            }
            return id;
        }
    }

    @Override
    public String checkBeforeDate(String msg, String status) {
        String dateFormat = "MM/dd/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        while (true) {
            String dateStr = checkString(msg,status);
            try {
                sdf.parse(dateStr);
            } catch (ParseException e) {
                System.err.println("Incorrect date ! Please enter again !");
            }
            return dateStr;
        }

    }

    @Override
    public String checkAfterDate(String msg, String pd, String status) {
        String dateFormat = "MM/dd/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        while (true) {
            String initDate = checkBeforeDate(msg,status);
            try {
                Date d1 = sdf.parse(initDate);
                String productionDate = pd;
                Date d2 = sdf.parse(productionDate);
                if (d1.compareTo(d2) < 0) {
                    System.out.println("Expiration date must large than production date ! Please enter again !");
                    continue;
                }
            } catch (ParseException ex) {
                System.err.println("Incorrect date ! Please enter again !");
                continue;
            }

        }
    }

    @Override
    public String checkType(String msg, String status) {
        while(true){
            String type = checkString(msg,status);
            if(type.isEmpty()){
                return type;
            }
            if((!type.equals("Daily")) || (!type.equals("Long")) ){
                System.err.println("Must input 1 in 2 type product is 'Daily' or 'Long' ! Please input again !");
                continue;
            }
            
            return type;
            
        }
    }

    @Override
    public String checkSize(String msg, String status) {
        while(true){
            String type = checkString(msg,status);
            
            if((!type.equals("Small")) || (!type.equals("Medium")) || (!type.equals("Large"))){
                System.err.println("Must input 1 in 3 size product is 'Small' or 'Medium' or 'Large' ! Please input again !");
                continue;
            }
            
            return type;
            
        }
    }

    @Override
    public int checkInt(String msg, int min, int max, String status) {
    
// vong lap su dung de nguoi dung nhap den khi dung 
        while (true) {
            
            // allow user input a string 
            String input_raw = checkString(msg,null);
            if(input_raw.isEmpty()&& status.equals("update")){
                return -1;
            }
            try {
                // loi nhap sai dinh dang so 
                int input = Integer.parseInt(input_raw);
                // loi nhap ngoai range cho phep
                if (input < min || input > max) {
                    System.err.println("Must input a number from " + min + "to " + max);
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {

                System.err.println("Must enter a number");
                continue;
            }

        }
    }

    @Override
    public double checkDouble(String msg, double min, double max, String status) {
          // vong lap su dung de nguoi dung nhap den khi dung 
        while (true) {

            // allow user input a string 
            String input_raw = checkString(msg,status);
            
            try {
                // loi nhap sai dinh dang so 
                double input = Double.parseDouble(input_raw);
                // loi nhap ngoai range cho phep
                if (input < min || input > max) {
                    System.err.println("Must input a number from " + min + "to " + max);
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {

                System.err.println("Must enter a number");
                continue;
            }

        }
    }

    @Override
    public boolean checkYesOrNo(String msg) {
while (true) {
            String input = checkString(msg,null);
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must input Y or N to select option");
                continue;
            }
        }    }

    @Override
    public boolean checkUpdateOrDelete(String msg) {
        while (true) {
            String input = checkString(msg,null);
            if (input.equalsIgnoreCase("U")) {
                return true;
            } else if (input.equalsIgnoreCase("D")) {
                return false;
            } else {
                System.err.println("Must input U or D to select option");
                continue;
            }
        }
    }

    @Override
    public boolean checkFileOrCollection(String msg) {
        while (true) {
            String input = checkString(msg,null);
            if (input.equalsIgnoreCase("F")) {
                return true;
            } else if (input.equalsIgnoreCase("C")) {
                return false;
            } else {
                System.err.println("Must input F or C to select option");
                continue;
            }
        }
    }
    

    
    
}


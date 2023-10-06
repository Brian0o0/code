/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import business_product.Product;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import utills.IValidation;
//import java.util.List;
//import business_products.Product;
//import business_products.Warehouse;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import utills.Status;

public class Validation implements IValidation {

    private Scanner sc = new Scanner(System.in);
//ham nhap va kiem tra string
    @Override
    public String checkString(String msg, Status status) {
        // vong lap su dung de nguoi dung nhap den khi dung 
        while (true) {
            System.out.println(msg);
            // allow user input a string 
            String input_raw = sc.nextLine();
            if (status.equals(Status.UPDATE) && input_raw.isEmpty()) {
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

//    @Override
//    public String checkProductCodeExist(String msg, List<Product> listProduct, Status status) {
//        while (true) {
//            // khai bao bien co de kiem tra xem co trung hay khong, neu trung thi flag = 1 
//            int flag = 0;
//            // NHAP ID DE CHECK 
//            String id = checkString(msg, status);
//
//            for (Product item : listProduct) {
//                if (item.getCode().equals(id)) {
//                    System.out.println("Id existed!!Please enter again");
//                    flag = 1;
//                    break;
//                }
//            }
//            if (flag == 1) {
//                continue;
//            }
//            return id;
//        }
//    }
//    @Override
//    public int checkReceiptCodeExist(String msg, List<Warehouse> listWareHouse) {
//        while (true) {
//            // khai bao bien co de kiem tra xem co trung hay khong, neu trung thi flag = 1 
//            int flag = 0;
//            // NHAP ID DE CHECK 
//            int id = sc.nextInt();
//
//            for (Warehouse item : listWareHouse) {
//                if (item.getCode() == id) {
//                    System.err.println("Receipt code existed!!Please enter again");
//                    flag = 1;
//                    break;
//                }
//            }
//            if (flag == 1) {
//                continue;
//            }
//            return id;
//        }
//    }
//    @Override
//    public String checkBeforeDate(String msg, Status status) {
//        String dateFormat = "MM/dd/yyyy";
//        DateFormat sdf = new SimpleDateFormat(dateFormat);
//        sdf.setLenient(false);
//        while (true) {
//            String dateStr = checkString(msg, status);
//            try {
//                sdf.parse(dateStr);
//            } catch (ParseException e) {
//                System.err.println("Incorrect date ! Please enter again !");
//            }
//            return dateStr;
//        }
//
//    }
    //ham kiem tra ngay het han va ngay san xuat
    public boolean checkInputDate(Date manu, Date exp) {
        boolean flag = true;
        if (manu.compareTo(exp) < 0) {
            flag = false;
        }
        return flag;
    }
//    @Override
//    public String checkAfterDate(String msg, String pd, Status status) {
//        String dateFormat = "MM/dd/yyyy";
//        DateFormat sdf = new SimpleDateFormat(dateFormat);
//        sdf.setLenient(false);
//        while (true) {
//            String initDate = checkBeforeDate(msg, status);
//            try {
//                Date d1 = sdf.parse(initDate);
//                String productionDate = pd;
//                Date d2 = sdf.parse(productionDate);
//                if (d1.compareTo(d2) < 0) {
//                    System.out.println("Expiration date must large than production date ! Please enter again !");
//                    continue;
//                }
//            } catch (ParseException ex) {
//                System.err.println("Incorrect date ! Please enter again !");
//                continue;
//            }
//
//        }
//    }
//Khi bạn đặt setLenient(false), đối tượng SimpleDateFormat sẽ trở nên nghiêm ngặt 
//hơn trong việc xác minh tính hợp lệ của ngày tháng và báo lỗi nếu dữ liệu
//đầu vào không hoàn toàn phù hợp với định dạng. Điều này có thể hữu ích
//trong một số trường hợp khi bạn muốn đảm bảo tính hợp lệ của dữ liệu đầu vào.
//ham nhan lua chon loai san pham tu nguoi dung
    @Override
    public String checkType(String msg, Status status) {
        while (true) {
            String type = checkString(msg, status);
            if (type.isEmpty()) {
                return type;
            }
            if (!(type.equals("daily") || type.equals("long"))) {
                System.err.println("Must input 1 in 2 types: 'daily' or 'long'! Please input again.");
            } else {
                return type; // Đã nhập đúng kiểu, trả về giá trị type.
            }
        }
    }

//    @Override
//    public String checkSize(String msg, Status status) {
//        while (true) {
//            String type = checkString(msg, status);
//
//            if ((!type.equals("Small")) || (!type.equals("Medium")) || (!type.equals("Large"))) {
//                System.err.println("Must input 1 in 3 size product is 'Small' or 'Medium' or 'Large' ! Please input again !");
//                continue;
//            }
//
//            return type;
//
//        }
//    }
    //ham nhap int va kiem tra
    @Override
    public int checkInt(String msg, int min, int max, Status status) {

// vong lap su dung de nguoi dung nhap den khi dung 
        while (true) {

            // allow user input a string 
            String input_raw = checkString(msg, status.NONE);
            if (input_raw.isEmpty() && status.equals(Status.UPDATE)) {
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

//    @Override
//    public double checkDouble(String msg, double min, double max, Status status) {
//        // vong lap su dung de nguoi dung nhap den khi dung 
//        while (true) {
//
//            // allow user input a string 
//            String input_raw = checkString(msg, status);
//            if (input_raw.isEmpty() && status.equals(Status.UPDATE)) {
//                return -1;
//            }
//            try {
//                // loi nhap sai dinh dang so 
//                double input = Double.parseDouble(input_raw);
//                // loi nhap ngoai range cho phep
//                if (input < min || input > max) {
//                    System.err.println("Must input a number from " + min + "to " + max);
//                    continue;
//                }
//                return input;
//            } catch (NumberFormatException e) {
//
//                System.err.println("Must enter a number");
//                continue;
//            }
//
//        }
//    }
    //ham kiem tra lua trong yes no cua nguoi dung
    @Override
    public boolean checkYesOrNo(String msg) {
        while (true) {
            String input = checkString(msg, Status.NONE);
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must input Y or N to select an option");
            }
        }
    }

//    @Override
//    public boolean checkUpdateOrDelete(String msg) {
//        while (true) {
//            String input = checkString(msg, null);
//            if (input.equalsIgnoreCase("U")) {
//                return true;
//            } else if (input.equalsIgnoreCase("D")) {
//                return false;
//            } else {
//                System.err.println("Must input U or D to select option");
//                continue;
//            }
//        }
//    }
    // ham nhan lua cho tu file hay tu collection
    @Override
    public boolean checkFileOrCollection(String msg) {
        while (true) {
            String input = checkString(msg, Status.ADD);
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
////ham kiem tra tao hoa don nhap hay xuat
//    public boolean checkImprotOrExport(String msg) {
//        while (true) {
//            String input = checkString(msg, Status.ADD);
//            if (input.equalsIgnoreCase("I")) {
//                return true;
//            } else if (input.equalsIgnoreCase("E")) {
//                return false;
//            } else {
//                System.err.println("Must input I or E to select option");
//                continue;
//            }
//        }
//    }
//ham lay ngay tu he thong
    public Date getdaynow() {
        Date currentDate = new Date();
        return currentDate;
    }
//ham kiem tra nguoi dung nhap ngay co hop le voi format
    public static boolean isDateValid(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(input);
            return true; // Định dạng hợp lệ
        } catch (ParseException e) {
            return false; // Định dạng không hợp lệ
        }
    }
    //Khi bạn đặt setLenient(false), đối tượng SimpleDateFormat sẽ trở nên nghiêm ngặt 
//hơn trong việc xác minh tính hợp lệ của ngày tháng và báo lỗi nếu dữ liệu
//đầu vào không hoàn toàn phù hợp với định dạng. Điều này có thể hữu ích
//trong một số trường hợp khi bạn muốn đảm bảo tính hợp lệ của dữ liệu đầu vào.
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Service;

import Application.Utilities.DataInput;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Components.SearchData;
import BussinessLayer.Entity.Product;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author NguyenDuy
 */
public class ProductService implements IProductService, Serializable {

    private ArrayList<Product> arr;
    private final DataInput di;
    private final DataValidation dv;
    private final SearchData sd;
    private final Scanner sc;

    public ProductService(ArrayList<Product> product) {
        arr = product;
        di = new DataInput();
        dv = new DataValidation();
        sd = new SearchData();
        sc = new Scanner(System.in);
    }

    public ArrayList<Product> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Product> arr) {
        this.arr = arr;
    }

    @Override
    public void addProduct() {
        String productID;
        String nameProduct;
        LocalDate manufacturingDate;
        LocalDate expirationDate;
        double price;
        int quantity;
        boolean check = true;
        boolean choice = true;
        while (choice) {
            productID = dv.inputProductID(arr);
            nameProduct = di.inputStringNotBlank("Enter name of product: ");
            manufacturingDate = di.inputDate("Enter date of manufacturing, must be 'day-month-year' format: ",
                    "dd-MM-yyyy");
            expirationDate = di.inputDate("Enter date of expiration, must be 'day-month-year' format: ", "dd-MM-yyyy");
            while (expirationDate.isBefore(manufacturingDate)) {
                if (dv.ValidManufacturingExpirationDate(expirationDate, manufacturingDate) == 1) {
                    manufacturingDate = di.inputDate("Enter date of manufacturing, must be 'day-month-year' format: ",
                            "dd-MM-yyyy");
                } else {
                    expirationDate = di.inputDate("Enter date of expiration, must be 'day-month-year' format: ",
                            "dd-MM-yyyy");
                }
            }
            price = di.inputDouble("Enter price of product: ", 0, 999999999);
            quantity = di.inputInteger("Enter quantity of product: ", 0, 999999999);
            arr.add(new Product(productID, nameProduct, manufacturingDate, expirationDate, price, quantity));
            choice = di.inputYN("Do you want to continue?(Y/N): ");
        }
    }

    @Override
    public void updateProduct() {
        System.out.println("Enter product you want to update: ");
        String id = sc.nextLine();
        Product item = sd.searchProductByID(arr, id);
        String nameProduct;
        LocalDate manufacturingDate;
        LocalDate expirationDate;
        double price;
        int quantity;
        boolean check = true;
        boolean choice = true;
        if (item != null) {
            System.out.println("Found! Here is product: ");
            graphic();
            System.out.printf("#%11s#%13s#%20s#%20s#%8s#%8s#\n", "Product ID", "Name Product", "Manufacturing Date",
                    "Expiration Date", "Price", "Quantity");
            System.out.println(item);
            graphic();
            nameProduct = dv.inputNameUpdate("Enter name product you want to update: ", item);
            manufacturingDate = di.inputDateUD("Enter date of manufacturing update, must be 'day-month-year' format: ",
                    "dd-MM-yyyy", item, 1);
            expirationDate = di.inputDateUD("Enter date of expiration  update, must be 'day-month-year' format: ",
                    "dd-MM-yyyy", item, 2);
            price = dv.inputPriceDB("Enter price you want to update: ", 0, 99999999, item);
            quantity = dv.inputQuantityDB("Enter quantity you want to update: ", 0, 99999999, item);
        } else {
            System.out.println("Not found!");
        }
    }

    @Override
    public void deleteProduct() {
        System.out.println("Enter id of product you want to delete: ");
        String id = sc.nextLine();
        Product item = sd.searchProductByID(arr, id);
        boolean choice = true;
        if (item != null) {
            System.out.println("Found! Here is product: ");
            graphic();
            System.out.printf("#%11s#%13s#%20s#%20s#%8s#%8s#\n", "Product ID", "Name Product", "Manufacturing Date",
                    "Expiration Date", "Price", "Quantity");
            System.out.println(item);
            graphic();
            choice = di.inputYN("You really want to delete(Y/N): ");
            if (choice) {
                arr.remove(item);
            }
        } else {
            System.out.println("Not found!");
        }
    }

    public void graphic() {
        for (int i = 0; i < 87; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    @Override
    public void showAllProduct() {
        graphic();
        System.out.printf("#%11s#%13s#%20s#%20s#%8s#%8s#\n", "Product ID", "Name Product", "Manufacturing Date",
                "Expiration Date", "Price", "Quantity");
        for (Product product : arr) {
            System.out.println(product);
        }
        graphic();
    }

    public void graphic2() {
        for (int i = 0; i < 98; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    public void totalPrice() {
        double total = 0;
        Collections.sort(arr);
        graphic2();
        System.out.printf("#%11s#%13s#%20s#%20s#%8s#%8s#%10s#\n", "Product ID", "Name Product", "Manufacturing Date",
                "Expiration Date", "Price", "Quantity", "Total Price");
        for (Product product : arr) {
            StringBuilder sb = new StringBuilder();
            total = product.getPrice() * product.getQuantity();
            sb.append(product).append(total);
            System.out.println(sb);
        }
        graphic2();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Service;

import BussinessLayer.Components.SearchData;
import BussinessLayer.Entity.Product;
import BussinessLayer.Entity.WarehouseExport;
import BussinessLayer.Entity.WarehouseImport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author NguyenDuy
 */
public class ReportService implements IReportSevice {

    private ArrayList<Product> arrProduct;
    private ArrayList<WarehouseExport> arrExport;
    private ArrayList<WarehouseImport> arrImport;
    private Scanner sc;
    private SearchData sd;

    public ReportService() {
    }

    public void graphic() {
        for (int i = 0; i < 87; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    
    public ReportService(ArrayList<Product> arrProduct, ArrayList<WarehouseExport> arrExport, ArrayList<WarehouseImport> arrImport) {
        this.arrProduct = arrProduct;
        this.arrExport = arrExport;
        this.arrImport = arrImport;
        sd = new SearchData();
        sc = new Scanner(System.in);
    }

    @Override
    public void expiredProducts() {
        graphic();
        System.out.printf("#%11s#%13s#%20s#%20s#%8s#%8s#\n", "Product ID", "Name Product", "Manufacturing Date", "Expiration Date", "Price", "Quantity");
        for (Product product : arrProduct) {
            if (product.getExpirationDate().isBefore(LocalDate.now())) {
                System.out.println(product);
            }
        }
        graphic();
    }

    @Override
    public void outOfStockProducts() {
        Collections.sort(arrProduct);
        graphic();
        System.out.printf("#%11s#%13s#%20s#%20s#%8s#%8s#\n", "Product ID", "Name Product", "Manufacturing Date", "Expiration Date", "Price", "Quantity");
        for (Product product : arrProduct) {
            if (product.getQuantity() < 3) {
                System.out.println(product);
            }
        }
        graphic();
    }

    @Override
    public void showImportReceipt() {
        graphic();
        for (WarehouseImport importx : arrImport) {
            System.out.println(importx);
        }
    }

    @Override
    public void showExportReceipt() {
        graphic();
        for (WarehouseExport export : arrExport) {
            System.out.println(export);
        }
    }

    @Override
    public void productSelling() {
        graphic();
        System.out.printf("#%11s#%13s#%20s#%20s#%8s#%8s#\n", "Product ID", "Name Product", "Manufacturing Date", "Expiration Date", "Price", "Quantity");
        for (Product product : arrProduct) {
            if (product.getExpirationDate().isAfter(LocalDate.now())) {
                System.out.println(product);
            }
        }
        graphic();
    }

    @Override
    public void searchIEReceipt() {
        System.out.println("Input Export or Import ID you want to search:(TYPE: E000000X or I000000X) ");
        String id = sc.nextLine().toUpperCase();
        if (id.startsWith("E")) {
            if (sd.searchWarehouseExportByID(arrExport, id) != null) {
                System.out.println("Found!");
                System.out.println(sd.searchWarehouseExportByID(arrExport, id));
            } else {
                System.out.println("Not Found!");
            }
        } else if (id.startsWith("I")) {
            if (sd.searchWarehouseImportByID(arrImport, id) != null) {
                System.out.println("Found!");
                System.out.println(sd.searchWarehouseImportByID(arrImport, id));
            } else {
                System.out.println("Not Found!");
            }
        } else {
            System.out.println("Not Found!");
        }
    }

}

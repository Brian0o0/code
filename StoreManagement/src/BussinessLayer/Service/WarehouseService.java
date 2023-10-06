/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Service;

import Application.UI.Menu;
import Application.Utilities.DataInput;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Components.SearchData;
import BussinessLayer.Entity.Product;
import BussinessLayer.Entity.WarehouseExport;
import BussinessLayer.Entity.WarehouseImport;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class WarehouseService implements IWarehouseService {

    private ArrayList<WarehouseImport> arrImport;
    private ArrayList<WarehouseExport> arrExport;
    private DataInput di;
    private DataValidation dv;
    private SearchData sd;
    private Menu menu;
    private ArrayList<Product> pr;
    public WarehouseService() {
    }
    
    public WarehouseService(ArrayList<WarehouseImport> arrImport, ArrayList<WarehouseExport> arrExport, ArrayList<Product> product) {
        this.arrImport = arrImport;
        this.arrExport = arrExport;
        di = new DataInput();
        dv = new DataValidation();
        sd = new SearchData();
        menu = new Menu();
        pr = product;
    }
   
    @Override
    public void createImportReceipt() {
        String id = dv.createWarehouseImportID(arrImport);
        ArrayList<Product> arrayPR = new ArrayList<>();
        boolean choice = true;
        while (choice) {
            System.out.println("Choice number of product you want to export: ");
            System.out.printf("NO #%11s#%13s#%20s#%20s#%8s#%8s#\n", "Product ID", "Name Product", "Manufacturing Date", "Expiration Date", "Price", "Quantity");
            Product product = (Product) menu.ref_getChoice(pr, 1, pr.size());
            product.setQuantity(product.getQuantity() + 1);
            if (sd.searchProduct(arrayPR, product) == null) {
                arrayPR.add(new Product(product.getProductID(), product.getNameProduct(), product.getManufacturingDate(), product.getExpirationDate(), product.getPrice(), 1));
            } else {
                Product temp = sd.searchProduct(arrayPR, product);
                arrayPR.get(arrayPR.indexOf(temp)).setQuantity(arrayPR.get(arrayPR.indexOf(temp)).getQuantity() + 1);
            }
            choice = di.inputYN("Do you want continue? (Y/N)");
        }
        LocalDateTime time = LocalDateTime.now();
        arrImport.add(new WarehouseImport(id, arrayPR, time));
        System.out.println("Sucessfully!");
    }
    
    @Override
    public void createExportReceipt() {
        String id = dv.createWarehouseExportID(arrExport);
        ArrayList<Product> arrayPR = new ArrayList<>();
        boolean choice = true;
        while (choice) {
            System.out.println("Choice number of product you want to export: ");
            System.out.printf("NO #%11s#%13s#%20s#%20s#%8s#%8s#\n", "Product ID", "Name Product", "Manufacturing Date", "Expiration Date", "Price", "Quantity");
            Product product = (Product) menu.ref_getChoice(pr, 1, pr.size());
            if (product.getQuantity() == 0) {
                System.out.println("This item is out of stock");
                return;
            } else {
                product.setQuantity(product.getQuantity() - 1);
                if (sd.searchProduct(arrayPR, product) == null) {
                    arrayPR.add(new Product(product.getProductID(), product.getNameProduct(), product.getManufacturingDate(), product.getExpirationDate(), product.getPrice(), 1));
                } else {
                    Product temp = sd.searchProduct(arrayPR, product);
                    arrayPR.get(arrayPR.indexOf(temp)).setQuantity(arrayPR.get(arrayPR.indexOf(temp)).getQuantity() + 1);
                }
            }
            choice = di.inputYN("Do you want continue? (Y/N)");
        }
        LocalDateTime time = LocalDateTime.now();
        arrExport.add(new WarehouseExport(id, arrayPR, time));
        System.out.println("Sucessfully!");
    }

}

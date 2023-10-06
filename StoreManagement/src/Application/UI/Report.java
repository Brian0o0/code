/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Entity.Product;
import BussinessLayer.Entity.WarehouseExport;
import BussinessLayer.Entity.WarehouseImport;
import BussinessLayer.Service.ReportService;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class Report {

    public Report() {
    }
    
    public void processReportManager(ArrayList<Product> arrProduct, ArrayList<WarehouseExport> arrExport, ArrayList<WarehouseImport> arrImport) {
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        ReportService rs = new ReportService(arrProduct, arrExport, arrImport);
        int choice;
        ops.add("Products that have expired");
        ops.add("The products that the store is selling.");
        ops.add("Products that are running out of stock (sorted in ascending order).");
        ops.add("Import receipt of a product.");
        ops.add("Export receipt of a product.");
        ops.add("Search Export or Import by ID. ");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoice(ops, 1, 7);
            switch (choice) {
                case 1:
                    rs.expiredProducts();
                    break;
                case 2:
                    rs.productSelling();
                    break;
                case 3:
                    rs.outOfStockProducts();
                    break;
                case 4:
                    rs.showImportReceipt();
                    break;
                case 5:
                    rs.showExportReceipt();
                    break;
                case 6:
                    rs.searchIEReceipt();
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 6));
    }
}

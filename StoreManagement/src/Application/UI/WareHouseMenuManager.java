/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Entity.Product;
import BussinessLayer.Entity.WarehouseExport;
import BussinessLayer.Entity.WarehouseImport;
import BussinessLayer.Service.WarehouseService;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class WareHouseMenuManager {

    public WareHouseMenuManager() {
    }
    
    public void processMenuWareHouseManager(ArrayList<WarehouseExport> arrExport, ArrayList<WarehouseImport> arrImport, ArrayList<Product> product){
        
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        WarehouseService wh = new WarehouseService(arrImport, arrExport, product);
        int choice;
        ops.add("Create an import receipt");
        ops.add("Create an export receipt.");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoice(ops, 1, 3);
            switch (choice) {
                case 1:
                    wh.createImportReceipt();
                    break;
                case 2:
                    wh.createExportReceipt();
                    break;
                
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 2));
    }
}

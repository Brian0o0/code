/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Entity.Product;
import BussinessLayer.Entity.WarehouseExport;
import BussinessLayer.Entity.WarehouseImport;
import DataLayer.ProductDao.ProductDao;
import DataLayer.WarehouseDao.WarehouseDao;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class Program {

    public static void main(String[] args) {
        String productFile = "product.dat";
        String warehouseFile = "warehouse.dat";
        WareHouseMenuManager whm = new WareHouseMenuManager();
        ProductMenuManager pmm = new ProductMenuManager();
        ArrayList<Product> product = new ArrayList<>();
        ArrayList<WarehouseExport> warehouseExports = new ArrayList<>();
        ArrayList<WarehouseImport> warehouseImports = new ArrayList<>();
        ProductDao pd = new ProductDao();
        WarehouseDao wd = new WarehouseDao();
        pd.loadDataFromFile(product, productFile);
        wd.loadDataFromFile(warehouseExports,warehouseImports, warehouseFile);
        Report rp = new Report();
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        int choice;
        ops.add("Manage products");
        ops.add("Manage Warehouse");
        ops.add("Report");
        ops.add("Store data to files");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoice(ops, 1, 5);
            switch (choice) {
                case 1:
                    pmm.processMenuProductManager(product);
                    break;
                case 2:
                    whm.processMenuWareHouseManager(warehouseExports,warehouseImports, product);
                    break;
                case 3:
                    rp.processReportManager(product, warehouseExports, warehouseImports);
                    break;
                case 4:
                    pd.saveDataFromFile(product, productFile);
                    wd.saveDataFromFile(warehouseExports,warehouseImports, warehouseFile);
                    break;

                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 4));
    }
}

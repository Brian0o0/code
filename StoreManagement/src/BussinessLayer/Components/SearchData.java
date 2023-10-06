/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Components;

import BussinessLayer.Entity.Product;
import BussinessLayer.Entity.WarehouseExport;
import BussinessLayer.Entity.WarehouseImport;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class SearchData {

    public Product searchProductByID(ArrayList<Product> arr, String id){
        for (Product product : arr) {
            if(product.getProductID().equals(id)){
                return product;
            }
        }
        return null;
    }
  
    public WarehouseImport searchWarehouseImportByID(ArrayList<WarehouseImport> arr, String id){
        for (WarehouseImport warehouseImport : arr) {
            if(warehouseImport.getImportID().equals(id)){
                return warehouseImport;
            }
        }
        return null;
    }

    public WarehouseExport searchWarehouseExportByID(ArrayList<WarehouseExport> arr, String id){
        for (WarehouseExport warehouseExport : arr) {
            if(warehouseExport.getExportID().equals(id)){
                return warehouseExport;
            }
        }
        return null;
    }

    public Product searchProduct(ArrayList<Product> arr, Product product){
        for (Product product1 : arr) {
            if(product1.getProductID().equals(product.getProductID())){
                return product1;
            }
        }
        return null;
    }
}

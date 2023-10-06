/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.ProductDao;

import BussinessLayer.Entity.Product;
import DataLayer.FileManager;
import java.util.List;

/**
 *
 * @author NguyenDuy
 */
public class ProductDao {
    private final FileManager fm;

    public ProductDao() {
        fm = new FileManager();
    }
    
    public boolean loadDataFromFile(List<Product> product ,String fName){
        return fm.loadDataFromFile(product, fName);
    }
   
    public boolean saveDataFromFile(List<Product> product ,String fName){
        return fm.saveDataToFile(product, fName, "Product save file successfull!");
    }
}

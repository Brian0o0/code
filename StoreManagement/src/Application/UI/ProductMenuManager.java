/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import BussinessLayer.Entity.Product;
import BussinessLayer.Service.ProductService;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class ProductMenuManager {

    public ProductMenuManager() {
    }
  
    public void processMenuProductManager(ArrayList<Product> arr){
        ArrayList<String> ops = new ArrayList<>();
        Menu menu = new Menu();
        int choice;
        ProductService ps = new ProductService(arr);
        ops.add("Add a product");
        ops.add("Update product information.");
        ops.add("Delete product.");
        ops.add("Show all product.");
        ops.add("Show total of product");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoice(ops, 1, 6);
            switch (choice) {
                case 1:
                    ps.addProduct();
                    break;
                case 2:
                    ps.updateProduct();
                    break;
                case 3:
                    ps.deleteProduct();
                    break;
                case 4:
                    ps.showAllProduct();
                    break;
                case 5:
                    ps.totalPrice();
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 5));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import business_product.Product;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IProductManage {
        //add product

    void addProduct(Product p);

    //update product
    Product updateProduct(Product oldProduct, String newName);

    //delete product]
    void deleteProduct(Product p);

    //show all prooduct
    void showAllProduct(boolean option);
    //load data of product from file
    public void loadData(List<String> dataFile);
}

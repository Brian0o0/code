/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author ASUS
 */
public interface Iservice {


//1. Manage products
    
//1.1. Add a product
    void addProduct();
//1.2. Update product information. 
    void updateProduct();
//1.3. Delete product. 
    void deleteProduct();
//1.4. Show all product.
void showAllProduct();


//2. Manage Warehouse

//2.1. Create an import receipt.
    void createImportReceipt();
//2.2. Create an export receipt. 
    void createExportReceipt();

//3. Report
    
//3.1. Products that have expired
    void showProductExpired();
//3.2. The products that the store is selling.
    void showProductSelling();
//3.3. Products that are running out of stock (sorted in ascending order).
    void showProductRunning();
//3.4. Import/export receipt of a product  
    void showReceiptProduct();
        
//4 save to File
    
    void loadData();
    void saveData();
}

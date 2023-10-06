package data_objects;

import business_objects.Product;

public interface IProductDao {
    /* Requested Method for Obj */
    // 1. find obj exist in the list ( private )
    int getIndex(String id); // Get input from service

    // 2. Create new Product into List
    // Get ID when create new Product
    String enterCode();

    // Get name of product when create new Product
    String enterProductName();

    void createNewProduct();

    // 3. Search Product by name
    void searchProduct(String name);

    // 4. Check product exist in file
    void checkExistInFile(String id);

    // 5. Update Product
    void updateProduct(String id);

    // 6. Delete Product
    void deleteProduct(String id);

    /* Methods for print out*/
    // print all product exist in program
    void printAllProduct();

    /* Read and write file */
    void readFromFile(String fileName);

    void writeToFile(String fileName);

    /* Decoration table */
    void openTable();

    void closeTable();

}

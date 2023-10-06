package action_service;

public interface IService {
    /* 1. Create New Product */
    void createNewProduct();

    /* 2. Check Exist Product */
    void checkExist();

    /* 3. Search Product By Name */
    void searchProduct();

    /* 4. Update Product */
    void updateProduct();

    /* 5. Remove Product */
    void removeProduct();

    /* 6.1 Read Product from file to program */
    void readFile(String fileName);

    /* 6.2 Write List Product into file */
    void writeFile(String fileName);

    /* 7. Print All Product */
    void printAllProduct();
}
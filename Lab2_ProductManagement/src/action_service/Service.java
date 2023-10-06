package action_service;

import data_objects.DaoFactory;
import data_objects.IDaoFactory;
import data_objects.IProductDao;
import tool_input.GetInput;

public class Service implements IService{
    IDaoFactory factory = new DaoFactory();
    IProductDao obj = factory.productDao();

    @Override
    public void createNewProduct() {
        obj.createNewProduct();
    }

    @Override
    public void checkExist() {
        String idCurrent = GetInput.getString("$ Enter ID of the Product: ");
        obj.checkExistInFile(idCurrent);
    }

    @Override
    public void searchProduct() {
        String nameCurrent = GetInput.getString("$ Enter Name of the Product: ");
        obj.searchProduct(nameCurrent);
    }

    @Override
    public void updateProduct() {
        String idCurrent = GetInput.getString("$ Enter ID of the Product: ");
        obj.updateProduct(idCurrent);
    }

    @Override
    public void removeProduct() {
        String idCurrent = GetInput.getString("$ Enter ID of the Product: ");
        obj.deleteProduct(idCurrent);
    }

    @Override
    public void readFile(String fileName) {
        obj.readFromFile(fileName);
    }

    @Override
    public void writeFile(String fileName) {
        obj.writeToFile(fileName);
        System.out.println(" ~ This list has saved successfully ~ ");
    }

    @Override
    public void printAllProduct() {
        obj.printAllProduct();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business_product.Item;
import business_product.Product;
import business_product.Warehouse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manage.ItemManage;
import manage.ProductManage;
import manage.WarehouseManage;
import report.Report;
import utills.FileManage;
import utills.Status;
import utils.Validation;

/**
 *
 * @author ASUS
 */
public class Service implements Iservice {

    static int idImport = 1000000;
    static int idExport = 5000000;
    FileManage fm = new FileManage();
    Validation valid = new Validation();
    ProductManage productManage = new ProductManage();
    WarehouseManage warehouseManage = new WarehouseManage();
    ItemManage itemManage = new ItemManage();
    List<Product> listProduct = productManage.listProduct;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
// ham nhap thong tin produc
    public Product inputProduct(Status satatus) {
        String code;
        while (true) {
            code = valid.checkString("Enter code of product:", satatus);
            //check code trung lap
            if (productManage.getProductByCode(code) != null) {
                System.out.println("Product code can not duplicate.Please enter again!");
                continue;
            }
            break;
        }
        String name = valid.checkString("Enter name product", satatus);
        Product newProduct = new Product(code, name);
        return newProduct;
    }
//ham them product da nhap vao danh sach
    @Override
    public void addProduct() {
        while (true) {
            //submenu
            Product newProduct = inputProduct(Status.ADD);
            //add the new product to collection.
            productManage.addProduct(newProduct);
            System.out.println("Added");
            //the application asks to continous create new product or go back to the main
            if (valid.checkYesOrNo("Do you want to continue to add product in the collection (Y/N): ")) {
                continue;
            }
            break;
        }
    }
//ham cap nhat product
    @Override
    public void updateProduct() {
        //yeu cau nguooi dung nhap code vao de tim kiem
        String code = valid.checkString("Enter code of product to update", Status.ADD);
        Product oldProduct = productManage.getProductByCode(code);
        if (oldProduct == null) //If product code does not exist, the notification “Product does not exist” message is shown.
        {
            System.out.println("Product does not exist in system.");
        } else {

            //Otherwise, user can input update information of product to update that product
            String newName = valid.checkString("Enter name to update: ", Status.UPDATE);
            Product newProduct = productManage.updateProduct(oldProduct, newName);
            System.out.println("Old product information has been updated:");
            System.out.println(newProduct);
            listProduct.remove(oldProduct);
            listProduct.add(newProduct);
        }

    }
//ham xoa product
    @Override
    public void deleteProduct() {
        //        Before the delete action is executed, the system must show confirm message.
        String code = valid.checkString("Enter code to delete", Status.DELETE);
        boolean flag = true;
        Product p = productManage.getProductByCode(code);
        if (p == null) {
            System.out.println("Product does not exist in system.");
            flag = false;
        } //only remove the product from the store's list when the import / export information for this product has not been generated.
        else if (warehouseManage.seachProductByCode(code)) {
            System.out.println("Product exist in receipt.");
            flag = false;
        }
        //the result of the delete must be show with success or fail message
        if (flag) {
            if (!valid.checkYesOrNo("Are you sure to remove product with code?(Y/N):")) {
                flag = false;
            }
            if (flag) {
                productManage.deleteProduct(p);
            }
        }
        if (flag) {
            System.out.println("Delete success!");
        } else {
            System.out.println("Delete Fail!");
        }
    }
//ham show tat ca san pham
    @Override
    public void showAllProduct() {
        boolean option = valid.checkFileOrCollection("Do you want show by File or Collection(F/C)");
        productManage.showAllProduct(option);
    }

    public String checkDate() {
        String input;
        while (true) {
            input = valid.checkString("Enter expiration date product (dd/MM/yyyy): ", Status.ADD);
            if (!valid.isDateValid(input)) {
                System.err.println("Invalid format, please try again!");
                continue;
            }
            break;
        }
        return input;
    }
//ham them item
    public Item inputItem(Status satatus) {
        String code = valid.checkString("Enter code of item:", satatus);
        String name = valid.checkString("Enter name of item: ", satatus);
        int quantity = valid.checkInt("Enter quantity of item", 0, 10000, satatus);
        String type = valid.checkType("Please select the product type you want to add 'daily'or'long': ", satatus);
        if (type.equals("long")) {
            Date manufactuDate = valid.getdaynow();
            Date expirationDate;
            while (true) {
                try {
                    expirationDate = df.parse(checkDate());//ep kieu du lieu nguoi dung nhap vao sang kieu du lieu date
                    if (valid.checkInputDate(manufactuDate, expirationDate)) {
                        System.out.println("The expiration date cannot be less than the manufacturing date!");
                        continue;
                    }
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Item newProduct = new Item(name, code, type, quantity, manufactuDate, expirationDate);
            return newProduct;
        } else if (type.equals("daily")) {
            Date manufactuDate = valid.getdaynow();
            // Tạo một đối tượng Calendar và thiết lập nó với ngày hiện tại
            Calendar calendar = Calendar.getInstance();
            // Tăng thêm 1 ngày
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date expirationDate = calendar.getTime();
            Item newProduct = new Item(name, code, type, quantity, manufactuDate, expirationDate);
            return newProduct;
        } else {
            return null;
        }
    }
//ham nhap thong tin hoa don nhap
    public Warehouse inputImportReceipt() {
        for (Warehouse wr : warehouseManage.listImport) {
            if (wr.getCode() == idImport) {
                idImport++;
            }
        }
        //id import bat dau tu 1000000 to 4999999 
        int code = idImport;
        Date date = valid.getdaynow();
        //tao list product  
        List<Item> listItem = new ArrayList<>();
        while (true) {
            Item item = inputItem(Status.ADD);
            listItem.add(item);
            itemManage.addItem(item);
            System.out.println("Add item to receipt import is successfull");
            if (valid.checkYesOrNo("Do you want to coutinue add product in receipt (Y/N): ")) {
                continue;
            }
            break;
        }
        Warehouse importReceipt = new Warehouse(code, date, listItem);
        return importReceipt;
    }
//ham tao hoa don nhap
    @Override

    public void createImportReceipt() {
        Warehouse importReceipt = inputImportReceipt();
        warehouseManage.createImportReceipt(importReceipt);
        System.out.println("Invoice created successfully");
    }
//ham show 1 san pham
    public void show(List<Product> list) {
        for (Product p : listProduct) {
            System.out.println(p);
        }
    }
//ham nhap thong tin hoa don xuat
    public Warehouse inputExportReceipt() {
        for (Warehouse wr : warehouseManage.listExport) {
            if (wr.getCode() == idExport) {
                idExport++;
            }
        }
        boolean flag = true;
        int code = idExport;
        Date date = valid.getdaynow();
        List<Item> listItem = new ArrayList<>();
        while (true) {
            itemManage.updateListItem();
            itemManage.sortList();
            itemManage.show(itemManage.getUnexpiredItem());
            String codeExp = valid.checkString("Enter code of item to add to receipt:", Status.ADD);
            Item item = itemManage.getProductByCodeInListItem(codeExp);
            if (item == null) {
                System.out.println("Product does not exist in system");
//                if (valid.checkYesOrNo("Do you want to coutinue add product in receipt (Y/N): ")) {
//                    continue;
//                }
//                break;
                continue;
            }
//            if (item.getExpirationDate().compareTo(date) < 0) {
//                System.out.println("Product has expired!");
//                if (valid.checkYesOrNo("Do you want to coutinue add product in receipt (Y/N): ")) {
//                    continue;
//                }
//                break;
//            }
            int itemQuantity = valid.checkInt("Enter quantity product", 0, 99999, Status.ADD);
            if (itemManage.checkQuantityFromListItem(itemQuantity, codeExp)) {
                for (Item item1 : itemManage.listItem) {
                    if (codeExp.equals(item1.getCode()) && item1.getExpirationDate().compareTo(valid.getdaynow()) > 0) {
                        if ((itemQuantity - item1.getQuantity()) <= 0 && item1.getQuantity() != 0) {
                            listItem.add(item1);
                            int currentQuantity = item1.getQuantity() - itemQuantity;
                            item1.setQuantity(currentQuantity);
                            break;
                        }
                        if (itemQuantity - item1.getQuantity() > 0 && item1.getQuantity() != 0) {
                            listItem.add(item1);
                            int temp = itemQuantity - item1.getQuantity();
                            if (temp >= 0) {
                                item1.setQuantity(0);
                            } else {
                                item1.setQuantity(item1.getQuantity() - itemQuantity);
                            }
                            itemQuantity = temp;
                        }
                    }
                    if (itemQuantity <= 0) {
                        break;
                    }
                }
            } else {
                flag = false;
                System.out.println("The quantity of products in stock is not enough!");
            }
            if (flag) {
                System.out.println("success");
            } else {
                System.out.println("failure");
            }

            if (valid.checkYesOrNo("Do you want to coutinue add product in receipt (Y/N): ")) {
                continue;
            }
            break;
        }

        Warehouse exportReceipt = new Warehouse(code, date, listItem);
        return exportReceipt;
    }
//ham tao tao hoa don xuat
    @Override
    public void createExportReceipt() {
        Warehouse importReceipt = inputExportReceipt();
        if (!(importReceipt.getListItems().isEmpty())) {
            warehouseManage.createExportReceipt(importReceipt);
            System.out.println("Invoice created successfully");
        } else {
            System.out.println("Invoice creation failed");
        }
    }
//ham show san pham het han
    @Override
    public void showProductExpired() {
        Report rp = new Report();
        itemManage.show(rp.showProductExpired(itemManage.listItem));
    }
//ham show san pham dang ban
    @Override
    public void showProductSelling() {
        Report rp = new Report();
        itemManage.show(rp.showProductSelling(itemManage.listItem));
    }
//ham show san pham da het ban
    @Override
    public void showProductRunning() {
        Report rp = new Report();
        itemManage.show(rp.showProductRunningOut(itemManage.listItem));
    }
//ham show can hoa don co chua san pham
    @Override
    public void showReceiptProduct() {
        Report rp = new Report();
        String code = valid.checkString("Eenter code of item:", Status.ADD);
        warehouseManage.showWarehouse(rp.showReceiptProduct(code, warehouseManage.getAllReceipt()));
    }
//ham load date from file
    @Override
    public void loadData() {
        itemManage.loadData(fm.loadFromFile("item.dat"));
        productManage.loadData(fm.loadFromFile("product.dat"));
        warehouseManage.loadData(fm.loadFromFile("warehouse.dat"), itemManage);
    }
//ham luu date to file
    @Override
    public void saveData() {
        itemManage.sortList();
        fm.saveToFile(itemManage.listItem, "item.dat");
        fm.saveToFile(productManage.listProduct, "product.dat");
        fm.saveToFile(warehouseManage.getAllReceipt(), "warehouse.dat");
        System.out.println("Stored data to file successfullyl");
    }

}

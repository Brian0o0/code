/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import business_product.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ProductManage implements IProductManage {

    public List<Product> listProduct;

    public ProductManage() {
        listProduct = new ArrayList<>();
    }
//ham them san pham
    @Override
    public void addProduct(Product p) {
        listProduct.add(p);
    }
//ham lay san pham tu sanh sach bang code
    public Product getProductByCode(String code) {
        for (Product p : listProduct) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }
//ham cap nhat san pham
    @Override
    public Product updateProduct(Product oldProduct, String newName) {
        String oldCode = oldProduct.getCode();
        Product newProduct = new Product(oldCode, newName);
        String name = newName;
        String oldName = oldProduct.getName();
        if (name.isEmpty()) {
            newProduct.setName(oldName);
        } 
        return newProduct;

    }
//ham soa san pham
    @Override
    public void deleteProduct(Product p) {
        listProduct.remove(p);
    }
//ham show tat ca san pham bang 2 lua trong tu flie hoac ti collection
    @Override
    public void showAllProduct(boolean option) {
        //true show fron file, false show from collection
        if (option) {
            showByFile("product.dat");
        } else {
            showByCollection();
        }
    }
//ham show 1 san pham
    public void show(List<Product> list) {
        for (Product p : listProduct) {
            System.out.println(p);
        }
    }
//ham show tu collection
    public void showByCollection() {
        show(listProduct);
    }
//ham show tu file
    public void showByFile(String fname) {
        List<Product> lisProductInFile = new ArrayList<>();
        try {
            File f = new File(fname); //create oject from class file
            if (!f.exists()) //checking the file
            {
                return;
            }
            BufferedReader bf = new BufferedReader(new FileReader(f)); //Read data from stream filereader.ReadLine read data line by line 1.
            String line = "";
            while ((line = bf.readLine()) != null) {
                String data[] = line.split(",");
                String code = data[0];
                String name = data[1];
                Product lp = new Product(code, name);
                lisProductInFile.add(lp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        show(lisProductInFile);
    }
//ham tai du lieu produc tu file len
    @Override
    public void loadData(List<String> dataFile) {
        for (String line : dataFile) {
            Product newProduct = null;
            String[] data = line.split(",");//sử dụng dấu phẩy (,) làm dấu phân tách để chia chuỗi line thành các phần tử riêng biệt
            String code = data[0];
            String name = data[1];
            newProduct = new Product(code, name);
            listProduct.add(newProduct);
        }
    }

}

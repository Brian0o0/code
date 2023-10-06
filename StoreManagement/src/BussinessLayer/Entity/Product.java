/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author NguyenDuy
 */
public class Product implements Serializable, Comparable<Product>{
    private String productID;
    private String nameProduct;
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;
    private double price;
    private int quantity;

    public Product(String productID, String nameProduct, LocalDate manufacturingDate, LocalDate expirationDate, double price, int quantity) {
        this.productID = productID;
        this.nameProduct = nameProduct;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.quantity = quantity;
        
    }
    public Product() {
    }
    
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return String.format("#%11s#%13s#%20s#%20s#%8.2f#%8d#", productID, nameProduct, manufacturingDate, expirationDate, price, quantity);
    }
    @Override
    public int compareTo(Product o) {
        if (this.getQuantity() <  o.getQuantity()) {
            return -1;
        }else if(this.getQuantity() >  o.getQuantity()){
            return 1;
        }else{
            return 0;
        }
    }
    
   
}

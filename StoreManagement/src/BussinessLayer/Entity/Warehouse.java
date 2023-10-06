/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class Warehouse implements Serializable{
    protected ArrayList<Product> product;
    private LocalDateTime IEDate;

    public Warehouse(ArrayList<Product> product, LocalDateTime IEDate) {
        this.product = product;
        this.IEDate = IEDate;
    }

    public Warehouse() {
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }

    public LocalDateTime getIEDate() {
        return IEDate;
    }

    public void setIEDate(LocalDateTime IEDate) {
        this.IEDate = IEDate;
    }

    
    
}

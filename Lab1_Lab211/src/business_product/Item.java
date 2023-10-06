/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_product;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
//class iformatin of item
public class Item extends Product {

    private int quantity;
    private String type;
    private Date manufacturingDate;
    private Date expirationDate;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public Item() {

    }

    public Item(String name, String code, String type, int quantity, Date manufacturingDate, Date expirationDate) {
        super(code, name);
        this.quantity = quantity;
        this.type = type;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return super.toString() + "," + type + "," + quantity + "," + df.format(manufacturingDate) + "," + df.format(expirationDate);
    }

}

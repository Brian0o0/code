/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
//class iformatin of warehouse
public class Warehouse {

    private int code;
    private Date time;
    private List<Item> listItems;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public Warehouse() {

    }

    public Warehouse(int code, Date time, List<Item> listItems) {
        this.code = code;
        this.time = time;
        this.listItems = listItems;
    }

    public int getCode() {
        return code;
    }

    public Date getTime() {
        return time;
    }

    public List<Item> getListItems() {
        return listItems;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        String result = code + "," + df.format(time);
        for (Item p : listItems) {
            String pCode = p.getCode();
            String pName = p.getName();
            int pQuantity = p.getQuantity();
            String pType = p.getType();
            Date pManu = p.getManufacturingDate();
            Date pExpi = p.getExpirationDate();
            result += ("," + pCode + "|" + pName + "|" + pType + "|" + pQuantity + "|" + df.format(pManu) + "|" + df.format(pExpi));
        }
        return result;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import business_product.Item;
import business_product.Warehouse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import utils.Validation;

/**
 *
 * @author ASUS
 */
public class Report implements IReport {

    Validation valid = new Validation();
//ham show san pham het han
    @Override
    public List<Item> showProductExpired(List<Item> listItem) {
        List<Item> listExpired = new ArrayList<>();
        Date timeNow = valid.getdaynow();
        for (Item p : listItem) {
            if (timeNow.compareTo(p.getExpirationDate()) > 0) {
                listExpired.add(p);
            }
        }
        if (listExpired.isEmpty()) {
            System.out.println("There are no expired products!");
        }
        return listExpired;
    }
//ham show san pham dang ban so luong >0 va chua het hat
    @Override
    public List<Item> showProductSelling(List<Item> listItem) {
        List<Item> listSelling = new ArrayList<>();
        Date dayNow = valid.getdaynow();
        for (Item p : listItem) {
            if (dayNow.compareTo(p.getExpirationDate()) <= 0 && p.getQuantity() > 0) {
                listSelling.add(p);
            }
        }
        if (listSelling.isEmpty()) {
            System.out.println("There are no products for sale!");
        }
        return listSelling;
    }
//ham show san pham het hang so luong<0 va xap xep theo thu tu so luong tang dan
    @Override
    public List<Item> showProductRunningOut(List<Item> listItems) {
        List<Item> listRunning = new ArrayList<>();
        for (Item p : listItems) {
            if (p.getQuantity() <= 3) {
                listRunning.add(p);
            }
        }//xap xep so luong san pham tang dan
        Comparator<Item> c = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.getQuantity() > o2.getQuantity()) {
                    return 1;
                } else if (o1.getQuantity() < o2.getQuantity()) {
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(listRunning, c);
        if (listRunning.isEmpty()) {
            System.out.println("No products!");
        }
        return listRunning;
    }
//ham show nhung hoa don co chuwa san pham
    @Override
    public List<Warehouse> showReceiptProduct(String code, List<Warehouse> warehouses) {
        List<Warehouse> listReceiptProduct = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            for (Item item : warehouse.getListItems()) {
                if (item.getCode().equals(code) ) {
                    listReceiptProduct.add(warehouse);
                }
            }
        }
        if (listReceiptProduct.isEmpty()) {
            System.out.println("No products!");
        }
        return listReceiptProduct;
    }

}

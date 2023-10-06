/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import business_product.Item;
import business_product.Product;
import business_product.Warehouse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class WarehouseManage implements IWarehouseManage {
//warehouse gom 2 danh sach,danh sach nhap va danh sach xuat
    public List<Warehouse> listImport;
    public List<Warehouse> listExport;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public WarehouseManage() {
        listImport = new ArrayList<>();
        listExport = new ArrayList<>();
    }

//ham them hoa don nhap vao danh sach
    @Override
    public void createImportReceipt(Warehouse newReceipt) {
        listImport.add(newReceipt);
    }
//ham them hoa don xuat thu danh sach
    @Override
    public void createExportReceipt(Warehouse newReceipt) {
        listExport.add(newReceipt);
    }
//ham tim kiem san pham tu warehouse bang code
    public boolean seachProductByCode(String code) {
        //gop hai danh sach lai de tim kiem product
        List<Warehouse> allReceipts = new ArrayList<Warehouse>(listImport);
        allReceipts.addAll(listExport);
        //duyet qua tung hoa don
        for (Warehouse receipt : allReceipts) {
            List<Item> list = receipt.getListItems();
            for (Item p1 : list) {
                if (p1.getCode().equals(code)) {
                    return true;
                }
            }
        }
        return false;
    }
//ham lay san pham tu warehouse
    public Item getProductInWarehouse(Item p) {
        //gop hai danh sach lai de tim kiem
        List<Warehouse> allReceipts = new ArrayList<Warehouse>(listImport);
        allReceipts.addAll(listExport);
        for (Warehouse receipt : allReceipts) {
            List<Item> list = receipt.getListItems();
            for (Item p1 : list) {
                if (p1.equals(p)) {
                    return p;
                }
            }
        }
        return null;
    }
//ham gop 2 danh sach xuat va nhap 
    public List<Warehouse> getAllReceipt() {
        List<Warehouse> allReceipts = new ArrayList<Warehouse>(listImport);
        allReceipts.addAll(listExport);
        return allReceipts;
    }
//ham show cac hoa don da duoc nhap trong warehouse
    public void showWarehouse(List<Warehouse> list) {
        for (Warehouse p : list) {
            System.out.println(p);
        }
    }
//ham tai du lieu cua warehouese tu file 
    @Override
    public void loadData(List<String> dataFile, ItemManage im) {
        for (String line : dataFile) {
            try {
                String[] info = line.split("[,]");
                int code = Integer.parseInt(info[0].trim());
                Date date = dateFormat.parse(info[1].trim());
                List<Item> items = new ArrayList<>();
                for (int i = 2; i < info.length; i++) {
                    String[] infoItem = info[i].split("[|]");
                    String iCode = infoItem[0];
                    String iName = infoItem[1];
                    String iType = infoItem[2];
                    int Iquanti = Integer.parseInt(infoItem[3].trim());
                    Date iManu = dateFormat.parse(infoItem[4].trim());
                    Date iExp = dateFormat.parse(infoItem[5].trim());
                    Item newItem = new Item(iName, iCode, iType, Iquanti, iManu, iExp);
                    items.add(newItem);
                }

                Warehouse w = new Warehouse(code, date, items);
                if (code > 999999 && code < 5000000) {
                    listImport.add(w);
                } else {
                    listExport.add(w);
                }
            } catch (ParseException ex) {
                Logger.getLogger(WarehouseManage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

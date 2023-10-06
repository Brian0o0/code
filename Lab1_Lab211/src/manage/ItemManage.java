/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import business_product.Item;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Validation;

/**
 *
 * @author ASUS
 */
public class ItemManage implements IItemManage {

    Validation valid = new Validation();
    public List<Item> listItem;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ItemManage() {
        listItem = new ArrayList<>();
    }
//ham lay nhung san pham chua hen han tu kho
    public List<Item> getUnexpiredItem() {
        List<Item> listItemToReceipt = new ArrayList<>();
        for (Item p : listItem) {
            if (p.getExpirationDate().compareTo(valid.getdaynow()) > 0) {
                listItemToReceipt.add(p);
            }
        }
        return listItemToReceipt;
    }
//ham lay san pham bang cach nho code
    public Item getProductByCodeInListItem(String code) {
        for (Item p : getUnexpiredItem()) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }
//ham im san pham ra man hinh
    public void show(List<Item> list) {
        for (Item p : list) {
            System.out.println(p);
        }
    }
//ham add a new item
    @Override
    public void addItem(Item p) {
        listItem.add(p);
    }
//ham deleta a item
    @Override
    public void deleteItem(Item p) {
        listItem.remove(p);
    }
//ham xoa cac san pham co so luong =0,va cap nhap danh sach moi
    public void updateListItem() {
        for (int i = 0; i < listItem.size(); i++) {
            if (listItem.get(i).getQuantity() == 0) {
                listItem.remove(i);
            }
        }
    }
// ham tai du lieu cua item tu file
    public void loadData(List<String> dataFile) {
        for (String line : dataFile) {
            try {
                Item newItem = null;
                String[] data = line.split(",");//sử dụng dấu phẩy (,) làm dấu phân tách để chia chuỗi line thành các phần tử riêng biệt
                String code = data[0];
                String name = data[1];
                String type = data[2];
                int quantity = Integer.parseInt(data[3]);
                Date pDate = dateFormat.parse(data[4]);
                Date eDate = dateFormat.parse(data[5]);
                newItem = new Item(name, code, type, quantity, pDate, eDate);
                listItem.add(newItem);
            } catch (ParseException ex) {
                Logger.getLogger(ItemManage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    Comparator<Item> com = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            if ((o1.getExpirationDate().compareTo(o2.getExpirationDate())) > 0) {
                return 1;
            } else if ((o1.getExpirationDate().compareTo(o2.getExpirationDate())) < 0) {
                return -1;
            }
            return 0;
        }
    };

    public void sortList() {
        Collections.sort(listItem, com);
    }
//ham kiem tra xem tong so luong san pham cua code nhap vao trong kho co du hay khong
    public boolean checkQuantityFromListItem(int quantity, String code) {
        int currentQanti = 0;
        boolean check = true;
        for (Item item : listItem) {
            if (item.getCode().equals(code)&& item.getExpirationDate().compareTo(valid.getdaynow()) > 0) {
                currentQanti += item.getQuantity();
            }
        }
        if (currentQanti < quantity) {
            check = false;
        }
        return check;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import business_product.Item;

/**
 *
 * @author ASUS
 */
public interface IItemManage {
    //add new Item
    void addItem(Item p);
    //delete item
    void deleteItem(Item p);
}

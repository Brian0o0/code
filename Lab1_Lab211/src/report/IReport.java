/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import business_product.Item;
import business_product.Warehouse;
import java.util.List;
import manage.ItemManage;

/**
 *
 * @author ASUS
 */
public interface IReport {

    //4.1.Products that have expired.
    List<Item> showProductExpired(List<Item> listItems);
//4.2.The products that the store is selling. (

    List<Item> showProductSelling(List<Item> listItems);
//4.3.Products that are running out of stock

    List<Item> showProductRunningOut(List<Item> listItems);
//4.4.Import/export receipt of a product.

    List<Warehouse> showReceiptProduct(String code,  List<Warehouse> warehouses);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import business_product.Warehouse;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IWarehouseManage {
//    Create an import receipt.

    void createImportReceipt(Warehouse newReceipt);
//    Create an export receipt

    void createExportReceipt(Warehouse newReceipt);
//    load date of warehouse fron file

    public void loadData(List<String> dataFile, ItemManage im);
}

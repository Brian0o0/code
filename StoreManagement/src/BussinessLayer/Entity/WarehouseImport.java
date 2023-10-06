/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class WarehouseImport extends Warehouse implements Serializable {

    private String importID;
    //Constructor-------------------------------------------------------------------------------------
    public WarehouseImport() {
    }

    public WarehouseImport(String importID, ArrayList<Product> product, LocalDateTime IEDate) {
        super(product, IEDate);
        this.importID = importID;
    }
    //Getter setter------------------------------------------------------------------------------------
    public String getImportID() {
        return importID;
    }

    public void setImportID(String importID) {
        this.importID = importID;
    }

    public String listAllProduct() {
        StringBuilder sb = new StringBuilder();
        for (Product item : product) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }

    public String graphic() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 87; i++) {
            sb.append("#");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#ID Import: " + importID + "###################################################################\n")
                .append("# Product ID# Name Product#  Manufacturing Date#     Expiration Date#   Price#Quantity#\n")
                .append(listAllProduct())
                .append("#####################################Import Date Time: " + getIEDate() + "########\n").append(graphic());
        return sb.toString();
    }

}

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
public class WarehouseExport extends Warehouse implements Serializable {

    private String exportID;
    //Constructor--------------------------------------------------------------------------------------------
    public WarehouseExport() {
    }

    public WarehouseExport(String exportID, ArrayList<Product> product, LocalDateTime IEDate) {
        super(product, IEDate);
        this.exportID = exportID;
    }
    //Getter, setter------------------------------------------------------------------------------------------
    public String getExportID() {
        return exportID;
    }

    public void setExportID(String exportID) {
        this.exportID = exportID;
    }
    public String listAllProduct(){
        StringBuilder sb = new StringBuilder();
        for (Product item : product) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
    public String graphic(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 87; i++) {
            sb.append("#");
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#ID export: " + exportID + "###################################################################\n")
                .append("# Product ID# Name Product#  Manufacturing Date#     Expiration Date#   Price#Quantity#\n")
                .append(listAllProduct())
                .append("#####################################Emport Date Time: " + getIEDate() + "#########\n").append(graphic());
        return sb.toString();
    }

}

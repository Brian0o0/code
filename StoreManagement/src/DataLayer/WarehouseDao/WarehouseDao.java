/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.WarehouseDao;

import BussinessLayer.Entity.WarehouseExport;
import BussinessLayer.Entity.WarehouseImport;
import DataLayer.FileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NguyenDuy
 */
public class WarehouseDao {

    private final FileManager fm;

    public WarehouseDao() {
        fm = new FileManager();
    }
    /**
     * Using third party to serialize object load from file
     * @param wareHouseExport
     * @param warehouseImports
     * @param fName 
     */
    public void loadDataFromFile(List<WarehouseExport> wareHouseExport, List<WarehouseImport> warehouseImports, String fName) {
        List<Object> objectsToSerialize = new ArrayList<>();
        fm.loadDataFromFile(objectsToSerialize, fName);
        for (Object object : objectsToSerialize) {
            if (object instanceof WarehouseExport) {
                wareHouseExport.add((WarehouseExport) object);
            } else if (object instanceof WarehouseImport) {
                warehouseImports.add((WarehouseImport) object);
            }
        }
    }
    /**
     * Using third party to serialize object save to file
     * @param wareHouseExport
     * @param warehouseImports
     * @param fName 
     */
    public void saveDataFromFile(List<WarehouseExport> wareHouseExport, List<WarehouseImport> warehouseImports, String fName) {
        List<Object> objectsToSerialize = new ArrayList<>();
        for (WarehouseExport whe : wareHouseExport) {
            objectsToSerialize.add(whe);
        }
        for (WarehouseImport whi : warehouseImports) {
            objectsToSerialize.add(whi);
        }
        fm.saveDataToFile(objectsToSerialize, fName, "Warehouse save file successfull!");
    }
}

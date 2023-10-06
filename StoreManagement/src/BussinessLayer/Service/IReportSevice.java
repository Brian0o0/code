/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BussinessLayer.Service;

/**
 *
 * @author NguyenDuy
 */
public interface IReportSevice {

    public void expiredProducts();
    public void productSelling();
    public void outOfStockProducts();

    public void showImportReceipt();
    public void searchIEReceipt();
    public void showExportReceipt();
}

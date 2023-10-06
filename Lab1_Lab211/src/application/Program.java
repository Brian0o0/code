/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import manage.ItemManage;
import services.Service;

/**
 *
 * @author ASUS
 */
public class Program {
    public void app(){
        Menu menu = new Menu();//dung de tao menu
        Service sv = new Service();
        ItemManage im = new ItemManage();
        sv.loadData();
        im.sortList();
        int choice;
//menu chinh gom 5 muc
        do {
            menu.addItem("=============== MAIN MENU ===============");
            menu.addItem("|        1.Manage products.             |");
            menu.addItem("|        2.Manage Warehouse.            |");
            menu.addItem("|        3.Report.                      |");
            menu.addItem("|        4.Store data to files.         |");
            menu.addItem("|        5.Close the application.       |");
            menu.addItem("================== END ==================");
            choice = menu.getChoice(1,5);
            if (choice < 0 || choice > 5) {
                System.out.println("Your selection is not valid, please choose again.");
//                sc.nextLine();
            }
            switch (choice) {
                case 1:
                    //menu nho cua muc 1 gom 5 muc
                    int choice1;
                    do {
                        menu.addItem("1. Add a product");
                        menu.addItem("2. Update product information.");
                        menu.addItem("3. Delete product.");
                        menu.addItem("4. Show all product");
                        menu.addItem("5. Return to the main screen");
                        choice1 = menu.getChoice(1,5);
                        if (choice1 < 0 || choice1 > 5) {
                            System.out.println("Your selection is not valid, please choose again");
                        }
                        switch (choice1) {
                            case 1:
                                sv.addProduct();
                                break;
                            case 2:
                                sv.updateProduct();
                                break;
                            case 3:
                                sv.deleteProduct();
                                break;
                            case 4:
                                sv.showAllProduct();
                                break;
                        }
                    } while (choice1 != 5);
                    break;
                case 2:
                    //menu nho cua muc 2 gom 3
                    int choice2;
                    do {
                        menu.addItem("1. Create an import receipt.");
                        menu.addItem("2. Create an export receipt.");
                        menu.addItem("3. Return to the main screen");
                        choice2 = menu.getChoice(1,3);
                        if (choice2 < 0 || choice2 > 3) {
                            System.out.println("Your selection is not valid, please choose again");
                        }
                        switch (choice2) {
                            case 1:
                                sv.createImportReceipt();
                                break;
                            case 2:
                                sv.createExportReceipt();
                                break;
                        }
                    } while (choice2 != 3);

                    break;
                case 3:
                    //menu nho cua muc 3 goom 5 muc
                    int choice3;
                    do {
                        menu.addItem("1. Products that have expired");
                        menu.addItem("2. The products that the store is selling.");
                        menu.addItem("3. Products that are running out of stock");
                        menu.addItem("4. Import/export receipt of a product.");
                        menu.addItem("5. Return to the main screen");
                        choice3 = menu.getChoice(1,5);
                        if (choice3 < 1 || choice3 > 5) {
                            System.out.println("Your selection is not valid, please choose again");
                        }
                        switch (choice3) {
                            case 1:sv.showProductExpired();

                                break;
                            case 2:sv.showProductSelling();

                                break;
                            case 3:sv.showProductRunning();

                                break;
                            case 4:sv.showReceiptProduct();

                                break;
                        }
                    } while (choice3 != 5);

                    break;
                case 4:
                    sv.saveData();
                    break;
            }
        } while (choice != 5);
    }
}

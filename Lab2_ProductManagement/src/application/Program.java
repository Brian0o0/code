package application;

import action_service.IService;
import action_service.Service;
import data_objects.Menu;
import tool_input.GetInput;

public class Program {
    public static void main(String[] args) {
        boolean flag = false;
        IService service = new Service();
        String fileName = "Product.dat";
        service.readFile(fileName);

        Menu menu = new Menu();
        menu.add("Create new Product");
        menu.add("Check Exists Product");
        menu.add("Search Product by Name");
        menu.add("Update Product");
        menu.add("Save Product to File");
        menu.add("Print All Product");
        menu.add("Quit Program");

        do {
            menu.showMenu();
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    do {
                        service.createNewProduct();
                    } while (!menu.confirmYesNo("  ~~ Do you want to go back to Menu? (Y/N): "));
                    break;
                case 2:
                    do {
                        service.checkExist();
                    } while (!menu.confirmYesNo(" ~~ Do you want to go back to Menu? (Y/N): "));
                    break;
                case 3:
                    do {
                        service.searchProduct();
                    } while (!menu.confirmYesNo(" ~~ Do you want to go back to Menu? (Y/N): "));
                    break;
                case 4:
                    int num = GetInput.getInt(" + 1. Update Product \n + 2. Delete Product \n => ", 1, 2);
                    switch (num) {
                        case 1:
                            do {
                                service.updateProduct();
                            } while (menu.confirmYesNo(" => Do you want to continue function 4.1? (Y/N): "));
                            break;
                        case 2:
                            do {
                                service.removeProduct();
                            } while (menu.confirmYesNo(" => Do you want to continue function 4.2? (Y/N): "));
                            break;
                    }
                    break;
                case 5:
                    service.writeFile(fileName);
                    break;
                case 6:
                    service.printAllProduct();
                    break;
                case 7:
                    flag = true;
                    System.out.println("      ------------------------");
                    System.out.println("==>   | *** Good Bye Sir *** |   <==");
                    System.out.println("      ------------------------");
                    break;
                default:
                    System.out.println(" ~ Pls choose from 1 to 7 only! ");
            }
        } while (!flag);

    }
}

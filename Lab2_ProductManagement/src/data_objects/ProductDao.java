package data_objects;

import business_objects.Product;
import tool_input.GetInput;

import java.io.*;
import java.util.ArrayList;

public class ProductDao implements IProductDao {
    /* 1. Attributes */
    // list for all product are loaded from file
    ArrayList<Product> listFile;

    final String ID_FORMAT = "^P\\d{3}$";
    final String NAME_FORMAT = "[a-z_-]{5,20}$";

    /* 2. Get and set */
    public void setListFile(ArrayList<Product> listFile) {
        this.listFile = listFile;
    }

    int countProInFile = 0;


    /* 3. Constructor */
    public ProductDao() {
        listFile = new ArrayList<>();
    }

    @Override
    public int getIndex(String id) {
        for (int i = 0; i < listFile.size(); i++) {
            if (listFile.get(i).getID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String enterCode() {
        do {
            String id_current = GetInput.getString("Enter Product ID: ");
            if (id_current.matches(ID_FORMAT)) {
                if (getIndex(id_current) == -1) {
                    return id_current;
                } else {
                    System.out.println("This ID is already exist");
                }
            } else {
                System.out.println("Your ID format is wrong! ~ ID format: P000 ___ 0 is a number from 0 to 9");
            }
        } while (true);
    }

    @Override
    public String enterProductName() {
        String nameCurrent;
        do {
            nameCurrent = GetInput.getString(" + Enter Product Name: ");
            if (nameCurrent.matches(NAME_FORMAT)) {
                return nameCurrent;
            } else {
                System.out.println(" -> Product Name must at least 5 five character and have no space or number!");
            }
        } while (true);
    }

    @Override
    public void createNewProduct() {
        ArrayList<String> listOpt = new ArrayList<>();
        listOpt.add("Available");
        listOpt.add("Not Available");
        Product obj = new Product(enterCode(), enterProductName(), GetInput.getInt(" + Enter Unit Price: ", 0, 10000),
                GetInput.getInt(" + Enter Quantity: ", 0, 10000), GetInput.getOption(" + Enter Status:", listOpt, false, null));
        listFile.add(obj);
        System.out.println("~ Create new Product successfully! ~");
    }

    @Override
    public void searchProduct(String name) {
        ArrayList<Integer> listIndex = new ArrayList<>();
        for (int i = 0; i < listFile.size(); i++) {
            if (listFile.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                listIndex.add(i);
            }
        }
        System.out.println("* There are " + listIndex.size() + " result for key word: " + name + " *");
        if (listIndex.size() == 1) {
            openTable();
            System.out.print(listFile.get(listIndex.get(0)).printfInfo(1));
            System.out.println();
            closeTable();
        } else if (listIndex.size() > 1) {
            System.out.println("\n-----------------------------------------------");
            System.out.printf(" %3s.   %-10s %-20s %-10s \n", "STT", "ID", "NAME", "PRICE");
            System.out.println("-----------------------------------------------");
            for (int i = 0; i < listIndex.size(); i++) {
                System.out.printf(" %3d.  %-10s %-20s    %-10s \n", i + 1, listFile.get(listIndex.get(i)).getID(), listFile.get(listIndex.get(i)).getName(), listFile.get(listIndex.get(i)).getPrice());
            }
            System.out.println("-----------------------------------------------");
            int choice = GetInput.getInt(" ~> Enter your choice: ", 1, listIndex.size());
            openTable();
            System.out.print(listFile.get(listIndex.get(choice - 1)).printfInfo(1));
            System.out.println();
            closeTable();
        }
    }

    @Override
    public void checkExistInFile(String id) {
        boolean flag = false;
        for (int i = 0; i < countProInFile; i++) { // check the file Product.dat ONLY!
            if (id.equalsIgnoreCase(listFile.get(i).getID())) {
                System.out.println(" -> Product with ID: " + id + " is exist in database (Product.dat)");
                flag = true;
                break;
            }
        }
        if (!flag) System.out.println(" -> Product with ID: " + id + " is NOT exist in database (Product.dat)");

    }

    @Override
    public void updateProduct(String id) {
        ArrayList<String> listOpt = new ArrayList<>();
        listOpt.add("Available");
        listOpt.add("Not Available");
        int index = getIndex(id);

        if (index != -1) {
            Product obj = listFile.get(index);
            obj.setName(GetInput.getNewString(" + Enter New Name for this Product: ", obj.getName()));
            obj.setPrice(GetInput.getNewInt(" + Enter New Unit Price: ", 0, 10000, obj.getPrice()));
            obj.setQuantity(GetInput.getNewInt(" + Enter new Quantity: ", 0, 10000, obj.getQuantity()));
            obj.setStatus(GetInput.getOption(" + Enter new Status: ", listOpt, true, obj.getStatus()));
            System.out.println(" => Update Product with ID: " + id + " successfully!");
        } else {
            System.out.println(" -> Product with ID: " + id + " is NOT exist in the List ~");
        }
    }

    @Override
    public void deleteProduct(String id) {
        int index = getIndex(id);
        System.out.println(" -> This Product will be removed and can not return ~ Are you sure to remove this Product? ");
        String choice = GetInput.getString("$ Enter Y to remove or any key to cancel: ");
        if (choice.equalsIgnoreCase("Y")) {
            if (index != -1) listFile.remove(index);
            else System.out.println(" => There is no Product with ID: " + id + " in the list!");
        } else System.out.println(" ~> Canceled request to remove product with ID: " + id);
    }

    @Override
    public void printAllProduct() {
        if (listFile.isEmpty()) {
            System.out.println(" => There are nothing in the database ~ Pls add new Infomation First!");
        } else {
            ArrayList<Product> temp = new ArrayList<>(listFile);
            ArrayList<String> listIDNew = new ArrayList<>();
            if (listFile.size() > countProInFile) {
                for (int i = countProInFile; i < listFile.size(); i++) {
                    listIDNew.add(listFile.get(i).getID());
                }
            }
            temp.sort((o1, o2) -> {
                if (o1.getQuantity() != o2.getQuantity()) {
                    return o2.getQuantity() - o1.getQuantity();
                } else {
                    return o1.getPrice() - o2.getPrice();
                }
            });
            openTable();
            for (int i = 0; i < temp.size(); i++) {
                System.out.print(temp.get(i).printfInfo(i + 1));
                for (String s : listIDNew) {
                    if (temp.get(i).getID().equalsIgnoreCase(s)) {
                        System.out.printf("%7s", "NEW");
                    }
                }
                System.out.println();
            }
            closeTable();
        }
    }

    @Override
    public void readFromFile(String fileName) {
        setListFile(new ArrayList<>());
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split("-");
                if (!arr[0].isEmpty()) {
                    listFile.add(new Product(arr[0], arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), arr[4]));
                    countProInFile++;
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeToFile(String fileName) {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Product obj : listFile) {
                printWriter.println(obj.getID() + "-" + obj.getName() + "-" + obj.getPrice() + "-" + obj.getQuantity() + "-" + obj.getStatus());
            }
            fileWriter.close();
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /* Table Decoration */
    @Override
    public void openTable() {
        System.out.println("\n----------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %2s. %-5s%-10s%-5s %5s%-10s%5s %5s%-10s%5s %5s%-10s%5s %5s%-10s%5s |\n", "No", " ", "ID", " ", " ", "NAME", " ", " ", "PRICE", " ", " ", "QUANTITY", " ", " ", "STATUS", " ");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void closeTable() {
        System.out.println("----------------------------------------------------------------------------------------------------------------\n");
    }
}
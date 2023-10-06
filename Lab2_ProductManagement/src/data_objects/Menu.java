package data_objects;

import tool_input.GetInput;

import java.util.ArrayList;

public class Menu extends ArrayList<String> implements IMenu{
    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public int getChoice() {
        return GetInput.getInt(" => Enter your choice: ", 1, this.size());
    }

    @Override
    public void showMenu() {
        System.out.println("------------------- MENU -------------------");
        for (int i = 0; i < this.size(); i++) {
            System.out.printf("| %2d.  %-25s %10s|\n", i+1, this.get(i), " ");
        }
        System.out.println("--------------------------------------------");
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        String flag = GetInput.getString(welcome);
        do {
            if (flag.equalsIgnoreCase("Y")) {
                return true;
            } else if (flag.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Just enter Y or N ~ Pls re-enter again...");
            }
        } while (true);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.UI;

import Application.Utilities.DataInput;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class Menu {

    private DataInput ip;

    public Menu() {
        ip = new DataInput();
    }

    public int int_getChoice(ArrayList options, int x, int y) {
        int respose;
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%2d.", i + 1);
            System.out.println(options.get(i));
        }
        respose = ip.inputInteger("Please input integer " + x + " to " + y + "", x, y);
        return respose;
    }

    public Object ref_getChoice(ArrayList options, int x, int y) {
        int respose;
        do {
            respose = int_getChoice(options, x, y);
        } while (respose < x || respose > y);
        return options.get(respose - 1);
    }
}

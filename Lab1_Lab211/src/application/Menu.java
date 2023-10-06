/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Scanner;
import utills.Status;
import utils.Validation;

/**
 *
 * @author ASUS
 */
public class Menu {
Validation valid = new Validation();
    //constructer
    public Menu() {
    }
    int choice;
    Scanner sc = new Scanner(System.in);
//in menu ra man hinh
    public void addItem(String s) {
        System.out.println(s);
    }
//ham nhap lua chon tu nguoi dung
    public int getChoice(int min, int max) {
        choice = valid.checkInt("Enter your choice: ", min, max, Status.ADD);
        System.out.println();
        return choice;
    }
}

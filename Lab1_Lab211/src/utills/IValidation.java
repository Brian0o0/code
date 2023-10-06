/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utills;


/**
 *
 * @author ASUS
 */
public interface IValidation {

    String checkString(String msg, Status status);

    String checkType(String msg, Status status);

    int checkInt(String msg, int min, int max, Status status);

    boolean checkYesOrNo(String msg);

    boolean checkFileOrCollection(String msg);

}

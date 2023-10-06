/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkvalid;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ICheckValid {

    String checkString(String msg, String status);

    String checkProductCodeExist(String msg, List<Product> listProduct, String status);

    String checkReceiptCodeExist(String msg, List<WareHouse> listWareHouse);

    String checkBeforeDate(String msg, String status);

    String checkAfterDate(String msg, String pd, String status);

    String checkType(String msg, String status);

    String checkSize(String msg, String status);

    int checkInt(String msg, int min, int max, String status);

    double checkDouble(String msg, double min, double max, String status);

    boolean checkYesOrNo(String msg);

    boolean checkUpdateOrDelete(String msg);

    boolean checkFileOrCollection(String msg);

}

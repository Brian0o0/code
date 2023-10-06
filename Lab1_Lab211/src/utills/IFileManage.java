/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utills;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IFileManage {

    void saveToFile(List objList, String fileName);
   
    List<String> loadFromFile(String fileName);
}

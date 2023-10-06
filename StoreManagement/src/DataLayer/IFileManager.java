/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer;

import java.util.List;

/**
 *
 * @author NguyenDuy
 */
public interface IFileManager {
    public <T> boolean loadDataFromFile(List<T> list, String FName);
    public <T> boolean saveDataToFile(List<T> list, String FName, String msg);
}

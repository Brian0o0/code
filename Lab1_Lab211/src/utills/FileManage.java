/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utills;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FileManage implements IFileManage {
//ghi dữ liệu từ một danh sách các đối tượng (được biểu diễn bằng danh sách objList) vào một tệp tin có tên fileName
    @Override
    public void saveToFile(List objList, String fileName) {
        try (FileWriter fw = new FileWriter(fileName)) {

            for (Object obj : objList) {
                fw.write(obj + "\n");//ghi mot doi tuong vao tep tim va them /n vao cuoi dem moi doi tuong duoc ghi tren 1 dong rieng biet
            }
        } catch (IOException ex) {
        }
    }
//đọc dữ liệu từ một tệp tin (file) có tên fileName và trả về danh sách các chuỗi (List<String>) chứa nội dung của tệp tin đó.
    @Override
    public List<String> loadFromFile(String fileName) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;//được khai báo để lưu trữ từng dòng dữ liệu được đọc từ tệp tin.
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
        } catch (IOException ex) {
        }

        return result;
    }

}

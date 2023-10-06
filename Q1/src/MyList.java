/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART=========
//===========================================================================
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xSource, int xPrice, int xType) {
        //You should write here appropriate statements to complete this function.
        if (xSource.charAt(0) == 'D') {
            return;
        }

        //----------------------------------------------------------------------    
        Node node = new Node(new Watermelon(xSource, xPrice, xType));
        if (this.isEmpty()) {
            this.tail = this.head = node;
        }
        this.tail.next = node;
        this.tail = node;
    }
//    void addFirst(String xSource, int xPrice, int xType){
//        
//    }

    void sortFull() {//sort full
        Node i = head;
        Node j = null;
        Watermelon tmp;
        while (i != null) {
            j = i.next;
            while (j != null) {
                if (i.info.price > j.info.price) {
                    tmp = i.info;
                    i.info = j.info;
                    j.info = tmp;
                }
                j = j.next;
            }
            i = i.next;
        }
    }

    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

    //==================================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Watermelon g;
        Watermelon h;
        g = new Watermelon("G", 2, 3);
        h = new Watermelon("H", 5, 6);
        Node x = new Node(g);
        Node y = new Node(h);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
          Your task is to insert statements here, just after this comment,
          to complete the question in the exam paper.*/
        y.next = x;
        x.next = head.next;
        head.next = y;

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    //==================================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        head.next.next = head.next.next.next.next;

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    //==================================================================
    public Node seach(int x, int y) {
        Node i = head;
        while (i != null) {
            if (i.info.price == x && i.info.type == y) {
                return i;
            }
            i = i.next;
        }
        return i;
    }

    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
           Your task is to insert statements here, just after this comment,
           to complete the question in the exam paper.*/
        Node i = head;
        Node j = null;
        Watermelon tmp;
        int count = 0;
        while (i != null && i != seach(6, 2)) { // Continue sorting until the first 4 elements are sorted
            j = i.next;
            while (j != null && j != seach(6, 2)) {
                if (i.info.type > j.info.type) {
                    tmp = i.info;
                    i.info = j.info;
                    j.info = tmp;
                }
                j = j.next;
            }
            i = i.next;
        }
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

}

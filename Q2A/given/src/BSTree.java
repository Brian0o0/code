/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xSource, int xPrice, int xType) {
        //You should insert here statements to complete this function
        //xPrice is the key of the tree.
        if (xSource.charAt(0) == 'B') {
            return;
        }
        Node x = new Node(new Watermelon(xSource, xPrice, xType));
        if (root == null) { //this.isEmpty()
            root = x;
            return;
        }
        Node f, p;
        p = root;
        f = null;
        while (p != null) {
            if (x.info.price == p.info.price) {// key trung
                System.out.println(" The key " + x.info.price + " already exists, no insertion");
                return;
            }
            f = p;
            if (x.info.price < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.info.price < f.info.price) {
            f.left = x;
        } else {
            f.right = x;
        }
    }//end insert

    //f2
    void preOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        if (p.info.type < 5) {
            fvisit(p, f);
        }

        preOrder2(p.left, f);
        preOrder2(p.right, f);
    }
    //

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f2() – Perform pre-order traversal from the root but display to file f2.txt
        //nodes with type<5 only.

        preOrder2(root, f);

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    public Node seach(Node root, int n) {
        if (root == null) {
            return null;
        }
        if (root.info.price == n) {
            return root;
        } else if (root.info.price > n) {
            return seach(root.left, n);
        }
        return seach(root.right, n);
    }

    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f3() – Suppose p is the 3rd node when performing the pre-order traversal of the tree.
        //Delete the node p  by copying.  
        Node r = seach(root, 1);

        if (r == null) {
            System.out.println("Node does not exist!");
            return;
        }
        if (root == null) {
            return;
        }
        //find father of node remove
        Node q = root;
        Node fa = null;
        while (q.info.price != 1) {
            fa = q;
            if (q.info.price > 1) {
                q = q.left;
            } else {
                q = q.right;
            }
        }
        if (fa == null) {
            root = null;
            return;
        }
        //case 1 r is a leap,if node r = fa.left thi gan root.left =null va nguoc lai
        if (r.left == null && r.right == null) {
            if (fa.left == r) {
                fa.left = null;
            } else {
                fa.right = null;
            }
        } //case 2 r has left childrent,ta lay node cha cua node can xoa gan voi node r.left
        else if (r.left != null && r.right == null) {
            if (fa.right == r) {
                fa.right = r.left;
            } else {
                fa.left = r.left;
            }
        } //case 3 r has rigtht childrent,ta lay node cha cua node can xoa gan voi node r.right
        else if (r.left == null && r.right != null) {
            if (fa.right == r) {
                fa.right = r.right;
            } else {
                fa.left = r.right;
            }
        } //case  r has 2 childrent
        else if (r.left == null && r.right == null) {
            // tim q la node lon nhat ben phai cua node bi xoa
            q = r.left;
            Node father = null;
            while (q.right != null) {//để tìm node lớn nhất bên phải của node r. 
                father = q;
                q = q.right;
            }
            r.info = q.info;
            if (father == null) {
                r.left = q.left;
            } else {
                father.right = q.left;

            }
//            Cuối cùng, bạn cần xóa node q khỏi cây. Nếu father vẫn là null,
//            điều này nghĩa là q là node con trái duy nhất của r, vì vậy bạn chỉ 
//            cần cập nhật r.left thành q.left. Nếu father không phải là null, 
//            bạn cập nhật father.right thành q.left, vì q là node lớn nhất bên phải của r,
//            nên không có node nào lớn hơn nó bên phải của q.
        }
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //------------------------------------------------------------------------------------
        //void f4() - rightmost node.
        Node p = root;
        Node q = null;
        if (p == null) {
            return;
        }
        while (p != null) {
            q = p;
            p = p.right;
        }
        fvisit(q, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================

    void printLeap(Node p, RandomAccessFile f) throws Exception {

        if (p == null) {
            return;
        }
        printLeap(p.left, f);
        printLeap(p.right, f);
        if (p.left == null && p.right == null) {
            fvisit(p, f);
        }
    }

    void f5() throws Exception {
        clear();
        loadData(17);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f5() - Leaf nodes
        printLeap(root, f);

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================

    void printInternal(Node p, RandomAccessFile f) throws Exception {

        if (p == null) {
            return;
        }
        if (p.left != null || p.right != null) {
            fvisit(p, f);
        }
        printInternal(p.left, f);
        printInternal(p.right, f);

    }

    void f6() throws Exception {
        clear();
        loadData(21);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f6() - Internal nodes
        printInternal(root, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================

    int countHeight(Node root) {
        Node p = root;
        if (p == null) {
            return 0;
        }
        int a = countHeight(p.right);
        int b = countHeight(p.left);
        if (a > b) {
            return a + 1;
        } else {
            return b + 1;
        }
    }

    void f7() throws Exception {
        clear();
        loadData(25);
        String fname = "f7.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //------------------------------------------------------------------------------------
        //void f7() - height of the tree

        f.writeBytes(countHeight(root) + "");
        //  
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================

    void findtLeap(Node p, RandomAccessFile f) throws Exception {

        if (p == null) {
            return;
        }
        findtLeap(p.left, f);
        findtLeap(p.right, f);
        int h = countHeight(root);
        if (p.left == null && p.right == null) {
            p.info.type += h;
        }
    }

    void f8() throws Exception {
        clear();
        loadData(29);
        String fname = "f8.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f8() - tim nut la, cong type voi chieu cao cua cay
        findtLeap(root, f);
        printLeap(root, f);

        //
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================
}

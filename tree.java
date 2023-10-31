/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication52;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class tree {

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
    }

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

//delete node
    public void deleteNode() {

        Node r = SearchPreOrder(root, 3);
        if (r == null) {
            System.out.println("Node does not exist!");
            return;
        }
        if (root == null) {
            return;
        }
        //find father of node remove
        Node q = root;
        Node fa = SearchPreOrder(root, 2);
//      Node fa =null;
//        while (q.info.price != 1) {
//            fa = q;
//            if (q.info.price > 1) {
//                q = q.left;
//            } else {
//                q = q.right;
//            }
//        }
        //case r is root
        if (fa == null) {
            root = null;
            return;
        }
        //case 1 r is a leap
        if (r.left == null && r.right == null) {
            if (fa.left == r) {
                fa.left = null;
            } else {
                fa.right = null;
            }
        } //case 2 r has left childrent
        else if (r.left != null && r.right == null) {
            if (fa.right == r) {
                fa.right = r.left;
            } else {
                fa.left = r.left;
            }
        } //case 3 r has rigtht childrent
        else if (r.left == null && r.right != null) {
            if (fa.right == r) {
                fa.right = r.right;
            } else {
                fa.left = r.right;
            }
        } //case  r has 2 childrent
        else if (r.left != null && r.right != null) {
            // q la node lon nhat ben phai cua node bi xoa
            q = r.left;
            Node father = null;
            while (q.right != null) {
                father = q;
                q = q.right;//node lon nhat ben phai
            }
            r.info = q.info;
            //cap nhat lai gia tri con cua nut thay the
            if (father == null) {
                r.left = q.left;
            } else {
                father.right = q.left;

            }
        }
    }
//add node theo thu tu preorder

    public List<Node> addPreOrder(Node p, List<Node> a) {
        if (p == null) {
            return null;
        }
        a.add(p);
        addPreOrder(p.left, a);
        addPreOrder(p.right, a);
        return a;
    }
//search node theo preorder

    public Node SearchPreOrder(Node p, int c) {
        List<Node> a = new ArrayList<>();
        if (c == 0) {
            return null;
        }
        addPreOrder(p, a);
        if (a.isEmpty()) {
            return null;
        }
        return a.get(c);
    }

    //add node theo thu tu Posorder
    public List<Node> addPosorder(Node p, List<Node> a) {
        if (p == null) {
            return null;
        }
        addPreOrder(p.left, a);
        addPreOrder(p.right, a);
        a.add(p);
        return a;
    }
//search node theo posorder

    public Node SearchPosorder(Node p, int c) {
        List<Node> a = new ArrayList<>();
        if (c == 0) {
            return null;
        }
        addPosorder(p, a);
        if (a.isEmpty()) {
            return null;
        }
        return a.get(c);
    }

    //add node theo thu tu Inorder
    public List<Node> addInorder(Node p, List<Node> a) {
        if (p == null) {
            return null;
        }
        addPreOrder(p.left, a);
        a.add(p);
        addPreOrder(p.right, a);
        return a;
    }
//search node theo preorder

    public Node SearchInorder(Node p, int c) {
        List<Node> a = new ArrayList<>();
        if (c == 0) {
            return null;
        }
        addInorder(p, a);
        if (a.isEmpty()) {
            return null;
        }
        return a.get(c);
    }
//add node theo thu tu breadth

    void addBreadth(Node p, List<Node> a) {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            a.add(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }
//search node theo breadth

    public Node SearchBreadth(Node p, int c) {
        List<Node> a = new ArrayList<>();
        if (c == 0) {
            return null;
        }
        addBreadth(p, a);
        if (a.isEmpty()) {
            return null;
        }

        return a.get(c);
    }

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
}

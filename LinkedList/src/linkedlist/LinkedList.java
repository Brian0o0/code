/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author ASUS
 */
public class LinkedList {

    Node head;
    Node tail;

    public LinkedList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    //seach Node by info
    public Node seach(int x) {
        Node i = head;
        while (i != null) {
            if (i.info == x) {
                return i;
            }
            i = i.next;
        }
        return i;
    }

    //add last
    public void addLast(int x) {
        Node newNode = new Node(x);
        //truong hop danh sach rong
        if (head == null) {
            head = tail = newNode;
            //truong hop co it nhat mot node
        } else {
            //them lien ket cho node moi
            tail.next = newNode;
            //thay doi lai tail
            tail = newNode;
        }
    }

    //add first
    public void addFirst(int x) {
        Node newNode = new Node(x);
        //truong hop danh sach rong
        if (head  ==null) {
            head = tail = newNode;
            //truong hop co it nhat mot node
        } else {
            //them lien ket cho node moi
            newNode.next = head;
            //thay doi lai head
            head = newNode;
        }
    }

    //remove 1 node cuoi cung
    public void deleteLast() {
        //truong hop rong
        if (isEmpty()) {
            return;
        }
        //truong hop chi co 1 node
        if (head == tail) {
            head = tail = null;
        } else {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            p.next = null;
            tail = p;
        }

    }

    //tinh so node tronh list
    public int countNode() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            ++count;
            temp = temp.next;
        }
        return count;
    }

    //chen 1 node vao vi tri k
    public void insertNode(int x, int k) {
        Node newNode = new Node(x);
        Node q = getNode(k);
        Node temp = q.next;
        q.next = newNode;
        newNode.next = temp;
    }

    //lay 1 gia tri o vi tri k
    public Node getNode(int k) {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            if (count == k) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    //sap xep tang dan

    //dao nguoc danh sach
    public void reverse() {
        int start =1;
        int end = countNode();
        while (start < end) {
            Node nodeStart = getNode(start);
            Node nodeEnd = getNode(end);
            swap(nodeStart, nodeEnd);
            start++;
            end--;
        }
    }

    public void swap(Node a, Node b) {
            int temp = a.info;
            a.info = b.info;
            b.info = temp;
    }

    //xoa 1 node tai vi tri
    public void deleteAt(int k) {
        if (k == 1) {
            head = head.next;
        } else {
            Node q = getNode(k - 1);
            if (q != null && q.next != null) {
                q.next = q.next.next;
            }
        }
    }

    //Duyet danh sach
    public void traverse() {
        //tao bien tam
        Node temp = head;
        //loop su dung de truy cap tung node den khi null
        while (temp != null) {
            System.out.print(temp.info + " ");
            //temp nhay qua node ke ben de doc thong tin
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.clear();
        ll.addFirst(23);
        ll.addLast(4);
        ll.addLast(3);
        ll.addLast(6);
        ll.addFirst(9);
        ll.traverse();

        System.out.println(ll.getNode(1));
        ll.reverse();
        ll.traverse();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication52;

/**
 *
 * @author ASUS
 */
public class Node {
  int info;
  Node next;

 public Node(int x) {
    info=x;
   }

    @Override
    public String toString() {
        return "Node{" + "info=" + info + ", next=" + next + '}';
    }
  
 }


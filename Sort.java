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
public class Sort {

    public void selectSort(int a[]) {
        int n = a.length;
        int i, j, k, min;
        for (i = 0; i < n; i++) {
            min = a[i];
            k = i;
            for (j = i + 1; j < n; j++) {
                if (a[j] < min) {
                    min = a[j];
                    k = j;
                }
            }
            if (k != i) {
                swap(a, i, k);
            }
        }
    }

    public void bubblerSort(int a[]) {
        int n = a.length;
        int i,j;
        for (i = 0; i < n-1; i++) {
            for (j = i+1;  j< n; j++) {
                if (a[i]>a[j]) {
                    swap(a, i, j);
                }
            }
        }
    }
        public void bubblerSort2(int a[]) {
        int n = a.length;
        int i,j;
        for (i = 0; i < n-1; i++) {
            for (j = n-1;  j> i; j--) {
                if (a[j]<a[j-1]) {
                    swap(a, j-1, j);
                }
            }
        }
    }
    public void insertSort(int a[]){
        int i,j,x;
        int n =a.length;
        for (i=0; i < n; i++) {
            x=a[i];
            j=i;
            while (j>0 && a[j-1]>x) {                
                a[j]=a[j-1];                
                a[--j]=x;
            }
        }
        
    }
    public void swap(int a[], int i, int j) {
        int t;
        t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void print(int a[]) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + ",");
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        int a[] = {1, 23, 43, 3, 2, 4, 6, 7,5,24,44};
        Sort s = new Sort();
        s.print(a);
//        s.selectSort(a);
//        s.insertSort(a);
        s.bubblerSort(a);
//        s.bubblerSort2(a);
        System.out.println();
        s.print(a);

    }

}

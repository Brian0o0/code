// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Node {
  Watermelon info;
  Node next;
  Node() {
   }
  Node(Watermelon x, Node p) {
    info=x;next=p;
   }
  Node(Watermelon x) {
    this(x,null);
   }
 }


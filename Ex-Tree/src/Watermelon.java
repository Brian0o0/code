// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Watermelon {
  String source;
  int price,type;
  Watermelon() {
   }
  Watermelon(String xSource, int xPrice, int xType){
    source=xSource;price=xPrice; type=xType;
   }
  public String toString(){
    return("(" +source+","+price + "," + type + ")");
   }
 }

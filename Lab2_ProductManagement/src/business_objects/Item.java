package business_objects;

public abstract class Item {
    // 1. Attributes
    protected String Name;
    protected int Quantity;

    // 2. Get and set
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    // 3. methods
    public abstract String printfInfo(int i);
}

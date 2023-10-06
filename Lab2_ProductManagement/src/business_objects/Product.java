package business_objects;

public class Product extends Item {
    // 1. Attributes
    protected String ID;
    protected int Price;
    protected String Status;

    // 2. Get and set methods
    public String getID() {
        return ID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }

    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    // 3. Constructors
    public Product() {
    }

    public Product(String ID, String Name, int Price, int Quantity, String status) {
        this.ID = ID;
        this.Name = Name;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Status = status;
    }

    // 4. Methods
    @Override
    public String printfInfo(int i) {
       String str = String.format("| %2d. %4s%-16s %4s%-16s %6s%-14d %8s%-12d %4s%-16s |", i, " ", ID, " ", Name, " ", Price, " ", Quantity, " ", Status);
       return str;
    }
}

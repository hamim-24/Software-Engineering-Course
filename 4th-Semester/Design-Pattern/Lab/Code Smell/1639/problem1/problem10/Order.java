import java.util.ArrayList;
import java.util.List;

public class Order {
    public List<Item> items;
    public double discount; 
    public Order(double discount) {
        this.discount = discount;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
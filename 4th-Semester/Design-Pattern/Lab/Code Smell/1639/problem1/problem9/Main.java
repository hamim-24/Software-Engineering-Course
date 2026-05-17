import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("Apple", 100);
        Item item2 = new Item("Banana", 110);
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        Order order = new Order(items);
        OrderManager orderManager = new OrderManager();
        orderManager.createOrder(order);
    }
}

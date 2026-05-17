package problem2;

public class Main {
    public static void main(String[] args) {
        Order order = new Order();
        Item item1 = new Item("Apple", 12);
        Item item2 = new Item("Banana", 15);

        order.addItem(item1);
        order.addItem(item2);

        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.processOrder(order);
    }
}

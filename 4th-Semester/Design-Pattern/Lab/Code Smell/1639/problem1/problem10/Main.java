public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("Apple", 100);
        Item item2 = new Item("Banana", 110);
        Order order = new Order(0.0);
        order.addItem(item1);
        order.addItem(item2);
        OrderHelper orderHelper = new OrderHelper();
        orderHelper.applyDiscount(order, .1);
    }
}

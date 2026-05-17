package problem2;
public class OrderProcessor {

    public void processOrder(Order order) {
        if (order.getItems().size() == 0) {
            System.out.println("Order is empty");
            return;
        }

        double total = 0;
        for (Item item : order.getItems()) {
            total += item.getPrice();
        }

        double tax = total * 0.2;

        System.out.println("Total with tax: " + (total + tax));
    }
}

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(
            "Alice Smith",
            "123 Main St, Dhaka",
            "+880-1234-567890",
            "alice@example.com");

        OrderItem orderItem = new OrderItem(
            "Laptop", 2, 850.00, 0.10
        );

        ShippingDetails shipping = new ShippingDetails(
            "Credit Card", "Express"
        );

        OrderProcessor orderProcessor = new OrderProcessor(customer, orderItem, shipping);
        orderProcessor.processOrder();
    }
}

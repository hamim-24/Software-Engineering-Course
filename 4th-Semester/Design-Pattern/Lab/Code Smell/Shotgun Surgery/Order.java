// Scenario:
// The `Order` class is responsible for several operations, including validating the order, processing payment, 
// and handling shipping. If you need to add a new feature to any of these operations (for example, updating payment 
// processing), you will have to touch multiple parts of the code in several different classes. This shotgun approach 
// increases the risk of errors and makes it harder to maintain and extend the system.
//
// Task for Students:
// Refactor the code by moving each responsibility into its own class. Create classes like `OrderValidator`, `PaymentProcessor`, 
// and `ShippingHandler`, and delegate responsibility for these operations to those classes. 
// Apply the **Extract Class** and **Move Method** refactoring techniques.

public class Order {
    private String customerName;
    private List<Item> items;

    public void validateOrder() {
        // Order validation logic
        System.out.println("Validating order...");
    }

    public void processPayment() {
        // Payment processing logic
        System.out.println("Processing payment...");
    }

    public void shipOrder() {
        // Shipping logic
        System.out.println("Shipping order...");
    }
}

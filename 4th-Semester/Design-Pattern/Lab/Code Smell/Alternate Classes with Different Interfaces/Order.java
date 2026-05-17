// Scenario:
// You have two classes, `OnlineOrder` and `OfflineOrder`, which implement different methods for the same process,
// but they share similar concepts. Due to their differing interfaces, it becomes difficult to handle orders 
// in a unified way. For instance, processing the payment for each type of order requires distinct methods, 
// which increases the complexity and introduces duplication.
//
// Task for Students:
// Refactor the code so that both `OnlineOrder` and `OfflineOrder` share a common interface for the payment 
// processing methods. Extract the common behaviors into an abstract class or interface, ensuring the same method 
// signature for both types of orders. 
// Apply the Extract Interface or Move Method refactoring techniques.

public class OnlineOrder {
    private String customerName;
    private String orderDetails;

    public void processPayment(double amount) {
        // Online payment processing
        System.out.println("Processing online payment of amount: " + amount);
    }
}

public class OfflineOrder {
    private String customerName;
    private String orderDetails;

    public void processPayment(double amount) {
        // Offline payment processing
        System.out.println("Processing offline payment of amount: " + amount);
    }
}

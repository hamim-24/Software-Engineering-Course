package problem1;
public class OfflineOrder implements Payable {
    private String customerName;
    private String orderDetails;

    public OfflineOrder(String customerName, String orderDetails) {
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }

    public void processPayment(double amount) {
        System.out.println("Processing offline payment of amount: " + amount);
    }
}

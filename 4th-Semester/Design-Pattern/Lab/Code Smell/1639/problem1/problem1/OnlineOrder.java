package problem1;
public class OnlineOrder implements Payable {
    private String customerName;
    private String orderDetails;

    public OnlineOrder(String customerName, String orderDetails) {
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }

    public void processPayment(double amount) {
        System.out.println("Processing online payment of amount: " + amount);
    }
}

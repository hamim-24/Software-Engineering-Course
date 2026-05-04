package task4_isp;

public class BkashPayment implements Payable {
    public void process(double amount) {
        System.out.println("Processing Bkash payment: $" + amount);
    }
}

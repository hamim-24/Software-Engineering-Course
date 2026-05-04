package task4_isp;

public class CreditCardPayment implements Payable, Refundable {
    public void process(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }

    public void refund(double amount) {
        System.out.println("Refunding credit card: $" + amount);
    }
}

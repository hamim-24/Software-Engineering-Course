package task1_srp;

public class PaymentProcessor {
    public void processPayment(String paymentType) {
        if (paymentType.equals("credit")) {
            System.out.println("Processing credit card payment");
        } else if (paymentType.equals("paypal")) {
            System.out.println("Processing PayPal payment");
        }
    }
}

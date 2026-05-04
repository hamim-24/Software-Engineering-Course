package task3_lsp;

public class PayPalPayment implements Payment {
    public void process(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }
}

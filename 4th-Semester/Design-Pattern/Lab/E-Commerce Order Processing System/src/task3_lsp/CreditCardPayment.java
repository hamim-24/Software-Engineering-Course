package task3_lsp;

public class CreditCardPayment implements Payment {
    public void process(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }
}

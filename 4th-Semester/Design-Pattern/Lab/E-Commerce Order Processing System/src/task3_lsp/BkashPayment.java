package task3_lsp;

public class BkashPayment implements Payment {
    public void process(double amount) {
        System.out.println("Processing Bkash payment: $" + amount);
    }
}

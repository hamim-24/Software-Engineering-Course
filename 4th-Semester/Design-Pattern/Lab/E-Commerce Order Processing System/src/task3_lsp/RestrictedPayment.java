package task3_lsp;

// BAD: Violates LSP — throws exception for large amounts, breaking substitutability
public class RestrictedPayment implements Payment {
    public void process(double amount) {
        if (amount > 500) {
            throw new IllegalArgumentException("Amount too large for restricted payment");
        }
        System.out.println("Processing restricted payment: $" + amount);
    }
}

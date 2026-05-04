package task4_isp;

// BAD: Fat interface forces all implementors to define refund(), even if not needed
public interface PaymentService {
    void process(double amount);
    void refund(double amount);
}

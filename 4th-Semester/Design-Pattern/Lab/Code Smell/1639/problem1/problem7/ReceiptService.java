public class ReceiptService {
    public Calculator calculator;
    public ReceiptService(Calculator calculator) {
        this.calculator = calculator;
    }
    public void printReceipt(Order order) {
        double total = calculator.calculateTotal(order);
        System.out.println("Receipt Total: " + total);
    }
}

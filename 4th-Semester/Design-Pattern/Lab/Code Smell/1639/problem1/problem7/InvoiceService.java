public class InvoiceService {
    public Calculator calculator;
    public InvoiceService(Calculator calculator) {
        this.calculator = calculator;
    }
    public void printInvoice(Order order) {
        double total = calculator.calculateTotal(order);
        System.out.println("Invoice Total: " + total);
    }
}

public class InvoiceGenerator {
    private static final double DISCOUNT_RATE = 0.1;
    private Order order;
    private double tax;
    public InvoiceGenerator(Order order, double tax) {
        this.tax = tax;
        this.order = order;
    }
    public void generateInvoice() {
        System.out.println("Total: " + calculateTotal());
        System.out.println("Discount: " + calculateDiscount());
        System.out.println("Tax: " + calculateTax());
        System.out.println("Final Amount: " + calculateFinalAmount());
    }
    private double calculateTotal() {
        double total = 0;
        for (Item item : order.getItems()) {
            total += item.getPrice();
        }
        return total;
    }
    private double calculateDiscount() {
        return calculateTotal() * DISCOUNT_RATE;
    }
    private double calculateTax() {
        return calculateTotal() * tax;
    }
    public double calculateFinalAmount() {
        return calculateTotal() - calculateDiscount() + calculateTax();
    }
}
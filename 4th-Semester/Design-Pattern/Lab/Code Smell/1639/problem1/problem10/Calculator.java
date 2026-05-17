public class Calculator {
    public Order order;
    public Calculator(Order order) {
        this.order = order;
    }
    public double calculateTotal() {
        double total = 0;
        for (Item item : order.items) {
            total += item.getPrice();
        }
        return total;
    }
    public double calculateDiscount(double rate) {
        return calculateTotal() * rate;
    }
}

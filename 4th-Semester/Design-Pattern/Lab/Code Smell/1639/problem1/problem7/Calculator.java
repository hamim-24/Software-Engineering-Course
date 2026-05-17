
public class Calculator {
    public double calculateSubTotal(Order order) {
        double total = 0;
        for(Item item : order.getItems()) {
            total += item.getPrice();
        }
        return total;
    }
    public double calculateTax(double subTotal) {
        return subTotal * utils.TAX;
    }
    public double calculateTotal(Order order) {
        double subTotal = calculateSubTotal(order);
        return subTotal + calculateTax(subTotal);
    }
}

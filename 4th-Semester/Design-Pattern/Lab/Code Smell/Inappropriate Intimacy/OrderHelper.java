public class OrderHelper {
    public void applyDiscount(Order order, double rate) {
        Calculator calculator = new Calculator(order);
        System.out.println("Discount: " + calculator.calculateDiscount(rate));
    }
}

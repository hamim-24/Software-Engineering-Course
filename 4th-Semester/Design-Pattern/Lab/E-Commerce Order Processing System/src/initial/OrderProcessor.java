package initial;

// BAD: All responsibilities in one class (violates SRP, OCP, DIP, etc.)
public class OrderProcessor {
    public double processOrder(String[][] order, String paymentType, String shippingType) {
        double total = 0;
        for (String[] item : order) {
            total += Double.parseDouble(item[1]);
        }
        if (total > 100) {
            total *= 0.9;
        }
        if (paymentType.equals("credit")) {
            System.out.println("Processing credit card payment");
        } else if (paymentType.equals("paypal")) {
            System.out.println("Processing PayPal payment");
        }
        if (shippingType.equals("standard")) {
            System.out.println("Standard shipping selected");
        } else if (shippingType.equals("express")) {
            System.out.println("Express shipping selected");
        }
        System.out.println("Sending email notification");
        return total;
    }
}

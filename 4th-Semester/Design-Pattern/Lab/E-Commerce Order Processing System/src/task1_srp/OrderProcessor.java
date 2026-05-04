package task1_srp;

public class OrderProcessor {
    private OrderCalculator calculator = new OrderCalculator();
    private DiscountService discountService = new DiscountService();
    private PaymentProcessor paymentProcessor = new PaymentProcessor();
    private ShippingService shippingService = new ShippingService();
    private NotificationService notificationService = new NotificationService();

    public double processOrder(String[][] order, String paymentType, String shippingType) {
        double total = calculator.calculateTotal(order);
        total = discountService.applyDiscount(total);
        paymentProcessor.processPayment(paymentType);
        shippingService.handleShipping(shippingType);
        notificationService.sendNotification();
        return total;
    }
}

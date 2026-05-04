package task2_ocp;

import task1_srp.OrderCalculator;
import task1_srp.DiscountService;
import task1_srp.NotificationService;

public class OrderProcessor {
    private OrderCalculator calculator = new OrderCalculator();
    private DiscountService discountService = new DiscountService();
    private NotificationService notificationService = new NotificationService();

    public double processOrder(String[][] order, Payment payment, Shipping shipping) {
        double total = calculator.calculateTotal(order);
        total = discountService.applyDiscount(total);
        payment.process();
        shipping.ship();
        notificationService.sendNotification();
        return total;
    }
}

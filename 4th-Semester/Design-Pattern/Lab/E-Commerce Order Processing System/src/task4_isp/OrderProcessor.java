package task4_isp;

import task1_srp.OrderCalculator;
import task1_srp.DiscountService;
import task1_srp.NotificationService;
import task2_ocp.Shipping;

public class OrderProcessor {
    private OrderCalculator calculator = new OrderCalculator();
    private DiscountService discountService = new DiscountService();
    private NotificationService notificationService = new NotificationService();

    public double processOrder(String[][] order, Payable payment, Shipping shipping) {
        double total = calculator.calculateTotal(order);
        total = discountService.applyDiscount(total);
        payment.process(total);
        shipping.ship();
        notificationService.sendNotification();
        return total;
    }
}

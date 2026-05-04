package task5_dip;

import task4_isp.Payable;
import task2_ocp.Shipping;

public class OrderProcessor {
    private final Calculator calculator;
    private final Discount discount;
    private final Notifier notifier;

    // DIP: dependencies injected via constructor, not instantiated internally
    public OrderProcessor(Calculator calculator, Discount discount, Notifier notifier) {
        this.calculator = calculator;
        this.discount = discount;
        this.notifier = notifier;
    }

    public double processOrder(String[][] order, Payable payment, Shipping shipping) {
        double total = calculator.calculateTotal(order);
        total = discount.applyDiscount(total);
        payment.process(total);
        shipping.ship();
        notifier.sendNotification();
        return total;
    }
}

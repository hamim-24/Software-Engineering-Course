public class OrderProcessor {
    private CalculateTotal calculateTotal;
    private IDiscountable iDiscountable;
	private IShipable iShipable;
    private Notification notification;

    public OrderProcessor(CalculateTotal calculateTotal, IDiscountable iDiscountable, IShipable iShipable) {
        this.calculateTotal = calculateTotal;
        this.iDiscountable = iDiscountable;
        this.iShipable = iShipable;
        this.notification = new Notification();
    }

    public double process(String[][] order, IPayable iPayable, IShipable iShipable) {
        double total = calculateTotal.calculate(order);
        double discountedPrice = iDiscountable.discountedPrice(total);
        iPayable.pay(total);
        iShipable.ship();
        notification.note();
        return discountedPrice;
    }
}

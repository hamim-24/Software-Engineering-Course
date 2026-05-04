public class Main {
    public static void main(String[] args) {
        String[][] order = {
            {"HP", "1000"},
            {"MAC", "1200"},
            {"LENOVO", "1500"}
        };
        CalculateTotal calculateTotal = new CalculateTotal();
        IPayable iPayable = new CreditPayment();
        IShipable iShipable = new ExpressShipment();
        IDiscountable iDiscountable = new Discoint();

        OrderProcessor orderProcessor = new OrderProcessor(calculateTotal, iDiscountable, iShipable);
        double price = orderProcessor.process(order, iPayable, iShipable);

        System.out.println("Discounted Price: " + price);
    }
}

public class Discoint implements IDiscountable{
    public double discountedPrice(double total) {
        if (total > 100) {
            total *= .9;
        }
        return total;
    }
}

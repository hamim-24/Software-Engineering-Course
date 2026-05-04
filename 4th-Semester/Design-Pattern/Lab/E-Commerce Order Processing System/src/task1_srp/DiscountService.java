package task1_srp;

public class DiscountService {
    public double applyDiscount(double total) {
        return total > 100 ? total * 0.9 : total;
    }
}

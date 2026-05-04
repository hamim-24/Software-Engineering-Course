package task5_dip;

public class PercentageDiscount implements Discount {
    public double applyDiscount(double total) {
        return total > 100 ? total * 0.9 : total;
    }
}

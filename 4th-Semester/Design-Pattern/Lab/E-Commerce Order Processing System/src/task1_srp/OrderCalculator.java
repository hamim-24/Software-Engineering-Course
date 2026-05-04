package task1_srp;

public class OrderCalculator {
    public double calculateTotal(String[][] order) {
        double total = 0;
        for (String[] item : order) {
            total += Double.parseDouble(item[1]);
        }
        return total;
    }
}

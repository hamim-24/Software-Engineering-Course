public class CalculateTotal {
    public double calculate(String[][] order) {
        double total = 0.0;
        for (String[] item : order) {
            total += Double.parseDouble(item[1]);
        }
        return total;
    }
}

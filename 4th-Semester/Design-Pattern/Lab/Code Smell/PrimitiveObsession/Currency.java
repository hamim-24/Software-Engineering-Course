public class Currency {
    private final String name;
    private final double rateFromUSD;
    public Currency(String name, double rateFromUSD) {
        this.name = name;
        this.rateFromUSD = rateFromUSD;
    }
    public String getName() {
        return name;
    }
    public double getRateFromUSD() {
        return rateFromUSD;
    }
}

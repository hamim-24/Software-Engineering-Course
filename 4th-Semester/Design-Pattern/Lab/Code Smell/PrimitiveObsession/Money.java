public final class Money {
    private final double cents;
    private final Currency currency;

    public Money(double cents, Currency currency) {
        this.cents = cents;
        this.currency = currency;
    }
    public double convertTo(Currency currency) {
        if (this.currency == currency) return this.cents;
        return this.cents / this.currency.getRateFromUSD() * currency.getRateFromUSD();
    }
    public double getCents() {
        return cents;
    }
    public Currency getCurrency() {
        return currency;
    }
    public double getAmount() {
        return this.cents * 100;
    }
}

public class ShippingDetails {
    private final String paymentMethod;
    private final String shippingMethod;

    public ShippingDetails(String paymentMethod, String shippingMethod) {
        this.paymentMethod  = paymentMethod;
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public String getShippingMethod() {
        return shippingMethod; 
    }
}

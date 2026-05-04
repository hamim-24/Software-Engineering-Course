package task1_srp;

public class ShippingService {
    public void handleShipping(String shippingType) {
        if (shippingType.equals("standard")) {
            System.out.println("Standard shipping selected");
        } else if (shippingType.equals("express")) {
            System.out.println("Express shipping selected");
        }
    }
}

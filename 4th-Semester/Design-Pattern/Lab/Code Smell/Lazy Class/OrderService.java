public class OrderService {

    public void placeOrder() {
        log("Order placed");
    }

    public void cancelOrder() {
        log("Order cancelled");
    }
    private void log(String message) {
        System.out.println("Audit Log: " + message);
    }
}
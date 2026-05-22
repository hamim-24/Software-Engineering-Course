public class PaymentModule {

    private final DatabaseConnectionManager dbManager;

    public PaymentModule() {
        this.dbManager = DatabaseConnectionManager.getInstance();
    }

    public void processPayment(int orderId, double amount, String method) {
        System.out.println("\n[PaymentModule] Processing payment of $" + amount
                + " for order #" + orderId + " via " + method);
        dbManager.connect();
        dbManager.beginTransaction();
        try {
            dbManager.executeQuery(
                    "INSERT INTO payments (order_id, amount, method, status) VALUES ("
                            + orderId + ", " + amount + ", '" + method + "', 'SUCCESS')"
            );
            dbManager.commitTransaction();
            System.out.println("[PaymentModule] Payment of $" + amount + " processed successfully.");
        } catch (Exception e) {
            dbManager.rollbackTransaction();
            System.out.println("[PaymentModule] Payment failed. Transaction rolled back.");
        }
    }

    public void refundPayment(int paymentId, double amount) {
        System.out.println("\n[PaymentModule] Refunding $" + amount
                + " for payment #" + paymentId);
        dbManager.connect();
        dbManager.beginTransaction();
        dbManager.executeQuery(
                "UPDATE payments SET status = 'REFUNDED' WHERE id = " + paymentId
        );
        dbManager.commitTransaction();
        System.out.println("[PaymentModule] Refund of $" + amount + " issued successfully.");
    }

    public void getPaymentHistory(int customerId) {
        System.out.println("\n[PaymentModule] Fetching payment history for customer #" + customerId);
        dbManager.connect();
        String result = dbManager.executeQuery(
                "SELECT * FROM payments WHERE customer_id = " + customerId
        );
        System.out.println("[PaymentModule] Payment history: " + result);
    }
}
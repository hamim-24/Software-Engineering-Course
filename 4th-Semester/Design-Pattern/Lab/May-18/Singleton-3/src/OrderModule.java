public class OrderModule {

    private final DatabaseConnectionManager dbManager;

    public OrderModule() {
        this.dbManager = DatabaseConnectionManager.getInstance();
    }

    public void createOrder(int customerId, int bookId, int quantity) {
        System.out.println("\n[OrderModule] Creating order — customer: "
                + customerId + ", book: " + bookId + ", qty: " + quantity);
        dbManager.connect();
        dbManager.beginTransaction();
        dbManager.executeQuery(
                "INSERT INTO orders (customer_id, book_id, quantity) VALUES ("
                        + customerId + ", " + bookId + ", " + quantity + ")"
        );
        dbManager.commitTransaction();
        System.out.println("[OrderModule] Order created successfully.");
    }

    public void updateOrderStatus(int orderId, String status) {
        System.out.println("\n[OrderModule] Updating order #" + orderId + " → " + status);
        dbManager.connect();
        dbManager.beginTransaction();
        dbManager.executeQuery(
                "UPDATE orders SET status = '" + status + "' WHERE id = " + orderId
        );
        dbManager.commitTransaction();
        System.out.println("[OrderModule] Order status updated.");
    }

    public void getOrderDetails(int orderId) {
        System.out.println("\n[OrderModule] Fetching details for order #" + orderId);
        dbManager.connect();
        String result = dbManager.executeQuery(
                "SELECT * FROM orders WHERE id = " + orderId
        );
        System.out.println("[OrderModule] Order details: " + result);
    }
}
public class Main {

    public static void main(String[] args) {

        System.out.println("════════════════════════════════════════════");
        System.out.println("  Online Bookstore — Singleton DB Manager   ");
        System.out.println("════════════════════════════════════════════");

        DatabaseConnectionManager db1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager db2 = DatabaseConnectionManager.getInstance();
        System.out.println("\nSingleton check — db1 == db2: " + (db1 == db2));

        CustomerModule  customerModule  = new CustomerModule();
        InventoryModule inventoryModule = new InventoryModule();
        OrderModule     orderModule     = new OrderModule();
        PaymentModule   paymentModule   = new PaymentModule();

        System.out.println("\n════ Inventory Operations ═══════════════════");
        inventoryModule.addBook("Clean Code", "Robert C. Martin", 100, 35.99);
        inventoryModule.addBook("Design Patterns", "GoF", 50, 49.99);
        inventoryModule.checkStock(1);
        inventoryModule.updateStock(1, 95);

        System.out.println("\n════ Customer Operations ════════════════════");
        customerModule.createCustomer("Alice Johnson", "alice@email.com");
        customerModule.searchCustomer("alice@email.com");

        System.out.println("\n════ Order Operations ═══════════════════════");
        orderModule.createOrder(1, 1, 2);
        orderModule.getOrderDetails(1);
        orderModule.updateOrderStatus(1, "SHIPPED");

        System.out.println("\n════ Payment Operations ═════════════════════");
        paymentModule.processPayment(1, 71.98, "Credit Card");
        paymentModule.getPaymentHistory(1);
        paymentModule.refundPayment(1, 71.98);

        System.out.println("\n════ DB Manager Final Status ════════════════");
        DatabaseConnectionManager.getInstance().printStatus();

        DatabaseConnectionManager.getInstance().closeConnection();

        System.out.println("\n════════════════════════════════════════════");
        System.out.println("  All modules shared ONE database connection ");
        System.out.println("════════════════════════════════════════════");
    }
}
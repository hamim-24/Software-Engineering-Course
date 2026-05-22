public class CustomerModule {

    private final DatabaseConnectionManager dbManager;

    public CustomerModule() {
        this.dbManager = DatabaseConnectionManager.getInstance();
    }

    public void createCustomer(String name, String email) {
        System.out.println("\n[CustomerModule] Creating customer: " + name);
        dbManager.connect();
        dbManager.beginTransaction();
        dbManager.executeQuery(
                "INSERT INTO customers (name, email) VALUES ('" + name + "', '" + email + "')"
        );
        dbManager.commitTransaction();
        System.out.println("[CustomerModule] Customer '" + name + "' created successfully.");
    }

    public void searchCustomer(String email) {
        System.out.println("\n[CustomerModule] Searching for customer: " + email);
        dbManager.connect();
        String result = dbManager.executeQuery(
                "SELECT * FROM customers WHERE email = '" + email + "'"
        );
        System.out.println("[CustomerModule] Search result: " + result);
    }
}
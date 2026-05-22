public class InventoryModule {

    private final DatabaseConnectionManager dbManager;

    public InventoryModule() {
        this.dbManager = DatabaseConnectionManager.getInstance();
    }

    public void addBook(String title, String author, int stock, double price) {
        System.out.println("\n[InventoryModule] Adding book: '" + title + "' by " + author);
        dbManager.connect();
        dbManager.beginTransaction();
        dbManager.executeQuery(
                "INSERT INTO books (title, author, stock, price) VALUES ('"
                        + title + "', '" + author + "', " + stock + ", " + price + ")"
        );
        dbManager.commitTransaction();
        System.out.println("[InventoryModule] Book added to inventory.");
    }

    public void updateStock(int bookId, int newStock) {
        System.out.println("\n[InventoryModule] Updating stock for book #" + bookId
                + " → " + newStock + " units");
        dbManager.connect();
        dbManager.beginTransaction();
        dbManager.executeQuery(
                "UPDATE books SET stock = " + newStock + " WHERE id = " + bookId
        );
        dbManager.commitTransaction();
        System.out.println("[InventoryModule] Stock updated successfully.");
    }

    public void checkStock(int bookId) {
        System.out.println("\n[InventoryModule] Checking stock for book #" + bookId);
        dbManager.connect();
        String result = dbManager.executeQuery(
                "SELECT stock FROM books WHERE id = " + bookId
        );
        System.out.println("[InventoryModule] Stock info: " + result);
    }
}
public class DatabaseConnectionManager {

    private static DatabaseConnectionManager instance;

    private String  databaseURL;
    private String  username;
    private String  password;
    private boolean connectionStatus;
    private int     activeConnectionCount;

    private DatabaseConnectionManager() {
        this.databaseURL          = "jdbc:mysql://localhost:3306/bookstore";
        this.username             = "admin";
        this.password             = "secret";
        this.connectionStatus     = false;
        this.activeConnectionCount = 0;
    }

    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
            System.out.println("[DatabaseConnectionManager] Singleton instance created.");
        }
        return instance;
    }

    public void connect() {
        if (!connectionStatus) {
            // Simulates opening a real JDBC connection
            connectionStatus = true;
            activeConnectionCount++;
            System.out.println("[DB] Connected to: " + databaseURL);
            System.out.println("[DB] Active connections: " + activeConnectionCount);
        } else {
            System.out.println("[DB] Already connected. Reusing existing connection.");
        }
    }

    public void disconnect() {
        if (connectionStatus) {
            connectionStatus = false;
            activeConnectionCount = Math.max(0, activeConnectionCount - 1);
            System.out.println("[DB] Disconnected. Active connections: " + activeConnectionCount);
        } else {
            System.out.println("[DB] No active connection to close.");
        }
    }

    public String executeQuery(String sql) {
        if (!connectionStatus) {
            System.out.println("[DB] Error: No active connection. Call connect() first.");
            return null;
        }
        System.out.println("[DB] Executing query: " + sql);
        return "ResultSet[" + sql + "]";
    }

    public void beginTransaction() {
        if (connectionStatus) {
            System.out.println("[DB] Transaction started.");
        } else {
            System.out.println("[DB] Cannot begin transaction — no active connection.");
        }
    }

    public void commitTransaction() {
        System.out.println("[DB] Transaction committed.");
    }

    public void rollbackTransaction() {
        System.out.println("[DB] Transaction rolled back.");
    }

    public void closeConnection() {
        disconnect();
        System.out.println("[DB] Connection closed and resources released.");
    }

    public boolean isConnected()          { return connectionStatus;      }
    public int     getActiveConnections() { return activeConnectionCount; }
    public String  getDatabaseURL()       { return databaseURL;           }

    public void printStatus() {
        System.out.println("\n── DB Manager Status ────────────────");
        System.out.println("  URL        : " + databaseURL);
        System.out.println("  Connected  : " + connectionStatus);
        System.out.println("  Active cons: " + activeConnectionCount);
        System.out.println("─────────────────────────────────────");
    }
}
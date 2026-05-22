public class HospitalDatabaseManager {

    private static HospitalDatabaseManager instance;

    private String  serverAddress;
    private boolean connectionStatus;
    private int     activeUserCount;
    private String  databaseConnection;

    private HospitalDatabaseManager() {
        this.serverAddress    = "db.hospital.local:5432";
        this.connectionStatus = false;
        this.activeUserCount  = 0;
        this.databaseConnection = null;
    }

    public static synchronized HospitalDatabaseManager getInstance() {
        if (instance == null) {
            instance = new HospitalDatabaseManager();
            System.out.println("[HospitalDB] Singleton instance created.");
        }
        return instance;
    }

    public void connect() {
        if (!connectionStatus) {
            databaseConnection = "Connection@" + serverAddress;
            connectionStatus   = true;
            activeUserCount++;
            System.out.println("[HospitalDB] Connected to " + serverAddress
                    + " | Active users: " + activeUserCount);
        } else {
            activeUserCount++;
            System.out.println("[HospitalDB] Reusing existing connection."
                    + " | Active users: " + activeUserCount);
        }
    }

    public String executeQuery(String sql) {
        if (!connectionStatus) {
            System.out.println("[HospitalDB] ERROR: No active connection.");
            return null;
        }
        System.out.println("[HospitalDB] QUERY  >> " + sql);
        return "ResultSet[" + sql + "]";
    }

    public void updatePatientRecord(String patientId, String field, String value) {
        connect();
        executeQuery("UPDATE patients SET " + field + " = '" + value
                + "' WHERE patient_id = '" + patientId + "'");
        System.out.println("[HospitalDB] Patient " + patientId
                + " — " + field + " updated to: " + value);
    }

    public void closeConnection() {
        activeUserCount = Math.max(0, activeUserCount - 1);
        if (activeUserCount == 0) {
            connectionStatus   = false;
            databaseConnection = null;
            System.out.println("[HospitalDB] All users disconnected. Connection closed.");
        } else {
            System.out.println("[HospitalDB] User released. Active users: " + activeUserCount);
        }
    }

    public void printStatus() {
        System.out.println("  Server    : " + serverAddress);
        System.out.println("  Connected : " + connectionStatus);
        System.out.println("  Users     : " + activeUserCount);
    }

    public boolean isConnected()      { return connectionStatus; }
    public int     getActiveUsers()   { return activeUserCount;  }
}
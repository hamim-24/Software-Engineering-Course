public class HospitalLogger {

    private static HospitalLogger instance;
    private int logCount = 0;

    private HospitalLogger() {
        System.out.println("[HospitalLogger] Logger initialized.");
    }

    public static synchronized HospitalLogger getInstance() {
        if (instance == null) {
            instance = new HospitalLogger();
        }
        return instance;
    }

    public void log(String module, String message) {
        logCount++;
        System.out.printf("[LOG #%03d] [%-18s] %s%n", logCount, module, message);
    }

    public void logError(String module, String error) {
        logCount++;
        System.out.printf("[ERR #%03d] [%-18s] ERROR: %s%n", logCount, module, error);
    }

    public int getLogCount() { return logCount; }
}
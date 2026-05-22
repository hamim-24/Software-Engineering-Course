import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class AttendanceLogger {

    private static volatile AttendanceLogger instance;

    private String logFilePath;
    private String currentSessionId;
    private String logLevel;
    private FileWriter file;

    private AttendanceLogger() {
        try {
            this.logLevel = "INFO";
            this.logFilePath = "attendance_log.txt";
            this.currentSessionId = generateSessionId();

            this.file = new FileWriter(logFilePath, true);

            System.out.println("Logger initialized. Session ID: "
                    + this.currentSessionId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateSessionId() {
        return "SessionId-" + UUID.randomUUID();
    }

    public static AttendanceLogger getInstance() {
        if (instance == null) {
            synchronized (AttendanceLogger.class) {
                if (instance == null) {
                    instance = new AttendanceLogger();
                }
            }
        }
        return instance;
    }

    public void logActivity(String module, String action, String details) {
        String entry = String.format("[%s] %s: %s%n",
                module, action, details);

        try {
            writeToFile(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String entry) throws IOException {
        file.write(entry);
        file.flush();
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public String getCurrentSessionId() {
        return currentSessionId;
    }
}
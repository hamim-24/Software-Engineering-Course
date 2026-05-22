public class AdminModule {
    private AttendanceLogger attendanceLogger = AttendanceLogger.getInstance();

    public void generateReport(String adminId, String reportType) {
        System.out.println("\n[AdminModule] Admin " + adminId + " generating report: " + reportType);

        attendanceLogger.logActivity("AdminModule", "REPORT_GENERATED",
                "Admin " + adminId + " generated report of type: " + reportType);
    }

    public void manageUsers(String adminId, String action, String targetUserId) {
        System.out.println("[AdminModule] Admin " + adminId + " performing user action: " + action);

        attendanceLogger.logActivity("AdminModule", "USER_MANAGED",
                "Admin " + adminId + " performed '" + action + "' on user " + targetUserId);
    }
}

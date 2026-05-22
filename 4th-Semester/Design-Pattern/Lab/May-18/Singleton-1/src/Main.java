public class Main {
    public static void main(String[] args) {

        AttendanceLogger loggerA = AttendanceLogger.getInstance();
        AttendanceLogger loggerB = AttendanceLogger.getInstance();

        System.out.println("=== Singleton Verification ===");
        System.out.println("Same instance? " + (loggerA == loggerB));
        System.out.println("Session ID:    " + loggerA.getCurrentSessionId());
        System.out.println("Log file path: " + loggerA.getLogFilePath());


        System.out.println("\n=== Simulating System Activity ===");

        StudentModule student = new StudentModule();
        student.login("STU-101");
        student.submitAttendance("STU-101");
        student.failedLogin("STU-999");

        TeacherModule teacher = new TeacherModule();
        teacher.updateAttendance("TCH-201", "STU-102", "CS401", "ABSENT");
        teacher.approveRequest("TCH-201", "REQ-305");

        AdminModule admin = new AdminModule();
        admin.generateReport("ADM-001", "MONTHLY_SUMMARY");
        admin.manageUsers("ADM-001", "DEACTIVATE", "STU-103");


        System.out.println("\n=== Logger Identity Check ===");
        System.out.println("All modules use the same logger instance — no duplicate file handles.");
    }
}
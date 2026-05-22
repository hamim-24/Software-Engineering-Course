public class StudentModule {

    private AttendanceLogger attendanceLogger = AttendanceLogger.getInstance();

    public void login(String studentId) {
        System.out.println("\n[StudentModule] Student " + studentId + " attempting login...");

        attendanceLogger.logActivity("StudentModule", "LOGIN", "Student " + studentId + " logged in successfully");
    }
    public void failedLogin(String studentId) {
        System.out.println("\n[StudentModule] Student " + studentId + " attempting login...");
        attendanceLogger.logActivity("StudentModule", "FAILED_LOGIN", "Student " + studentId + " failed login");
    }

    public void submitAttendance(String studentId) {
        System.out.println("\n[StudentModule] Student " + studentId + " submitting attendance...");

        attendanceLogger.logActivity("StudentModule", "SUBMIT", "Student " + studentId + " submitted");
    }
}

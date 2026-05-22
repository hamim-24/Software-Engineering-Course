public class TeacherModule {
    private AttendanceLogger attendanceLogger = AttendanceLogger.getInstance();

    public void updateAttendance(String teacherId, String studentId, String courseId, String status) {
        System.out.println("\n[TeacherModule] Teacher " + teacherId + " updating attendance...");

        attendanceLogger.logActivity("TeacherModule", "ATTENDANCE_MODIFIED",
                "Teacher " + teacherId + " changed status of student " + studentId
                        + " in course " + courseId + " to " + status);
    }
    public void approveRequest(String teacherId, String requestId) {
        System.out.println("[TeacherModule] Approving request " + requestId);

        attendanceLogger.logActivity("TeacherModule", "REQUEST_APPROVED",
                "Teacher " + teacherId + " approved attendance request " + requestId);
    }
}

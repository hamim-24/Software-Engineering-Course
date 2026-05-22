public class AuthenticationManager {

    private static AuthenticationManager instance;
    private int activeSessionCount = 0;

    private AuthenticationManager() {
        System.out.println("[AuthManager] Authentication service initialized.");
    }

    public static synchronized AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public boolean authenticate(String userId, String role) {
        boolean valid = userId != null && !userId.isEmpty();
        activeSessionCount += valid ? 1 : 0;
        HospitalLogger.getInstance().log("AuthManager",
                (valid ? "GRANTED" : "DENIED") + " — " + role + " [" + userId + "]");
        return valid;
    }

    public void logout(String userId) {
        activeSessionCount = Math.max(0, activeSessionCount - 1);
        HospitalLogger.getInstance().log("AuthManager",
                "Session ended for: " + userId);
    }

    public int getActiveSessionCount() { return activeSessionCount; }
}
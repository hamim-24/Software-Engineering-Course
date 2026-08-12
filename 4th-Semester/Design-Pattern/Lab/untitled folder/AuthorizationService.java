public class AuthorizationService {
    public boolean hasAccess(User user) {
        if (user.isAuthenticated()) {
            System.out.println("Permission granted.");
            return true;
        }
        System.out.println("Permission Denied.");
        return false;
    }
}

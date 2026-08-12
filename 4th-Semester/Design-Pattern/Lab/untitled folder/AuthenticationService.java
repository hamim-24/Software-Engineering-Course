public class AuthenticationService {
    public boolean login(User user) {
        user.setAuthenticated(true);
        System.out.println(user.getName() + " authenticated.");
        return true;
    }
}

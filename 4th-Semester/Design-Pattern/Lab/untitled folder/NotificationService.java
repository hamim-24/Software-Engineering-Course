public class NotificationService {
    public void notify(User user, String msg) {
        System.out.println("Notification -> "
            + user.getName() + " : " + msg
        );
    }
}

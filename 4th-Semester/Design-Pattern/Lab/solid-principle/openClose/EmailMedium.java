package openClose;

public class EmailMedium implements NotificationMedium {
    public void send(String message) {
        System.out.println("Emailing: " + message);
    }
}

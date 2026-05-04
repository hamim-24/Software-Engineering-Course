package dependencyInvertion;

import openClose.NotificationMedium;

public class NotificationManager {
    private NotificationMedium medium;
    public NotificationManager(NotificationMedium medium) {
        this.medium = medium;
    }    
    public void notifyUser(String message) {
        medium.send(message);
    }
}

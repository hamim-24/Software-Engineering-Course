package interfaceSegregation;

import openClose.*;

public class AdvanceMail implements NotificationMedium, ICanAttachFile {
    public void send(String message) {
        System.out.println("Sending Mail: " + message);
    }
    public void attachFile(String fileName) {
        System.out.println("Attached file: " + fileName);
    }
}

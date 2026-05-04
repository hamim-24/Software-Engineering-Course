package openClose;

class SMSMedium implements NotificationMedium {
    public void send(String message) {
        System.out.println("Texting: " + message);
    }
}

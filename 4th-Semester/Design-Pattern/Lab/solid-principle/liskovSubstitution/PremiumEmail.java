package liskovSubstitution;

import openClose.EmailMedium;

public class PremiumEmail extends EmailMedium {
    @Override
    public void send(String message) {
        System.out.println("Sending High speed mail: " + message);
    }
}
package Factory;

public class CarRide implements Ride {
    @Override
    public void book(String pickup, String destination) {
        System.out.println("Car booked from " + pickup + " to " + destination);
    }
}

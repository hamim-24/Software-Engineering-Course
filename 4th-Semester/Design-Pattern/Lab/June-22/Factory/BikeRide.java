package Factory;

public class BikeRide implements Ride {
    @Override
    public void book(String pickup, String destination) {
        System.out.println("Bike booked from " + pickup + " to " + destination);
    }
}

package Factory;

public abstract class RideFactory {
    public abstract Ride createRide();

    public void requestRide(String pickup, String destination) {
        Ride ride = createRide();
        ride.book(pickup, destination);

        System.out.println("Ride confirmed.");
    }
}

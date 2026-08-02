package Factory;

public class CarRideFactory extends RideFactory {

    @Override
    public Ride createRide() {
        return new CarRide();
    }
}

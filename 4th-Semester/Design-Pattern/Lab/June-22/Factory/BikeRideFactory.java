package Factory;

public class BikeRideFactory extends RideFactory {

    @Override
    public Ride createRide() {
        return new BikeRide();
    }
}


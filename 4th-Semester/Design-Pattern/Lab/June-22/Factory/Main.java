package Factory;

public class Main {
    public static void main(String[] args) {
        RideFactory factory = new CarRideFactory();
        factory.requestRide("Dhanmondi", "Gulshan");
        
        factory = new BikeRideFactory();
        factory.requestRide("Mirpur", "Farmgate");
    }
}

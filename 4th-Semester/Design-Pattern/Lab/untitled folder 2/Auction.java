import java.util.ArrayList;
import java.util.List;

public class Auction {

    private final String auctionId;
    private final String productName;
    private final double startingPrice;

    private double currentHighestBid;
    private Bidder currentHighestBidder;

    private boolean active;

    private final List<AuctionObserver> observers;

    public Auction(String auctionId, String productName, double startingPrice) {
        this.auctionId = auctionId;
        this.productName = productName;
        this.startingPrice = startingPrice;
        this.currentHighestBid = startingPrice;
        this.active = false;
        this.observers = new ArrayList<>();
    }

    public void startAuction() {
        if (active) {
            System.out.println("Auction is already active.");
            return;
        }

        active = true;

        System.out.println("\n================================");
        System.out.println("Auction Started");
        System.out.println("Product: " + productName);
        System.out.println("Starting Price: $" + startingPrice);
        System.out.println("================================\n");

        notifyBidders();
    }

    public void closeAuction() {

        if (!active) {
            System.out.println("Auction is already closed.");
            return;
        }

        active = false;

        System.out.println("\n================================");
        System.out.println("Auction Closed");
        System.out.println("Product: " + productName);

        if (currentHighestBidder != null) {
            System.out.println(
                    "Winner: " + currentHighestBidder.getName()
            );
            System.out.println(
                    "Winning Bid: $" + currentHighestBid
            );
        } else {
            System.out.println("No bids were placed.");
        }

        System.out.println("================================\n");

        notifyBidders();
    }
    public void registerBidder(AuctionObserver observer) {

        if (!observers.contains(observer)) {
            observers.add(observer);

            System.out.println(
                    "Observer registered successfully."
            );
        }
    }

    public void removeBidder(AuctionObserver observer) {

        if (observers.remove(observer)) {
            System.out.println(
                    "Observer removed successfully."
            );
        }
    }

    public void notifyBidders() {

        List<AuctionObserver> currentObservers = new ArrayList<>(observers);

        for (AuctionObserver observer : currentObservers) {
            observer.update(this);
        }
    }

    public void placeBid(Bidder bidder, double bidAmount) {

        System.out.println("\n" + bidder.getName() + " is attempting to bid $" + bidAmount);

        if (!active) {
            System.out.println("Bid rejected: Auction is not active.");
            return;
        }

        if (bidAmount <= currentHighestBid) {
            System.out.println("Bid rejected: Bid must be greater than " + "the current highest bid of $" + currentHighestBid);
            return;
        }

        currentHighestBid = bidAmount;
        currentHighestBidder = bidder;

        System.out.println("Valid bid accepted!");

        System.out.println("New highest bid: $" + currentHighestBid);

        System.out.println("Highest bidder: " + currentHighestBidder.getName());

        notifyBidders();
    }

    public String getAuctionId() {
        return auctionId;
    }

    public String getProductName() {
        return productName;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public double getCurrentHighestBid() {
        return currentHighestBid;
    }

    public Bidder getCurrentHighestBidder() {
        return currentHighestBidder;
    }

    public boolean isActive() {
        return active;
    }
}
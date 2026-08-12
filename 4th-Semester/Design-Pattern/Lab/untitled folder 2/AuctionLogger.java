import java.time.LocalDateTime;

public class AuctionLogger
        implements AuctionObserver {

    @Override
    public void update(Auction auction) {

        System.out.println("\n[AUCTION LOGGER]");
        System.out.println("[" + LocalDateTime.now() + "]");
        System.out.println("Auction ID: " + auction.getAuctionId());
        System.out.println("Product: " + auction.getProductName());
        System.out.println("Highest Bid: $" + auction.getCurrentHighestBid());
        if (auction.getCurrentHighestBidder() != null) {

            System.out.println("Highest Bidder: " + auction.getCurrentHighestBidder().getName());
        }
        System.out.println( "Status: " + (auction.isActive() ? "ACTIVE" : "CLOSED"));
    }
}
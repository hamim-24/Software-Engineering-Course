public class EmailNotificationService
        implements AuctionObserver {

    @Override
    public void update(Auction auction) {

        System.out.println("\n[EMAIL NOTIFICATION]");

        System.out.println("Email sent to interested users.");
        System.out.println("Auction: " + auction.getAuctionId());
        System.out.println("Product: " + auction.getProductName());
        System.out.println("New Highest Bid: $" + auction.getCurrentHighestBid());
        if (auction.getCurrentHighestBidder() != null) {
            System.out.println("Highest Bidder: " + auction.getCurrentHighestBidder().getName());
        }
    }
}
public class AuctionStatistics
        implements AuctionObserver {

    private int updateCount = 0;

    @Override
    public void update(Auction auction) {

        updateCount++;

        System.out.println("\n[STATISTICS]");
        System.out.println("Auction: " + auction.getAuctionId());
        System.out.println("Total Updates Received: " + updateCount);
        System.out.println("Current Highest Bid: $" + auction.getCurrentHighestBid());
    }
}
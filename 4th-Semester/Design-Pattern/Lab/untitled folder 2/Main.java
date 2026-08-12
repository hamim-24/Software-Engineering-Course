public class Main {
    public static void main(String[] args) {
        Auction auction = new Auction( "AUC-101", "MacBook Pro M3", 1200.00);
        Bidder bidder1 = new Bidder( "B001", "Alice", "alice@email.com");

        Bidder bidder2 = new Bidder( "B002", "Bob", "bob@email.com");

        Bidder bidder3 = new Bidder("B003", "Charlie", "charlie@email.com");
        EmailNotificationService emailService = new EmailNotificationService();

        AuctionLogger logger = new AuctionLogger();

        auction.registerBidder(bidder1);
        auction.registerBidder(bidder2);
        auction.registerBidder(bidder3);
        auction.registerBidder(emailService);
        auction.registerBidder(logger);
        auction.startAuction();
        auction.placeBid(bidder1, 1300);
        auction.placeBid(bidder2, 1400);
        auction.placeBid(bidder3, 1500);
        auction.placeBid(bidder1, 1400);
        System.out.println("\nRemoving Bob from the auction...");
        auction.removeBidder(bidder2);
        auction.placeBid(bidder1, 1600);
        AuctionStatistics statistics = new AuctionStatistics();
        auction.placeBid(bidder3, 1700);
        auction.closeAuction();
        auction.placeBid(bidder1, 1800);
    }
}
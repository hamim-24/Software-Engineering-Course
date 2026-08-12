public class Bidder implements AuctionObserver {

    private final String bidderId;
    private final String name;
    private final String contactInformation;

    private boolean participating;

    public Bidder( String bidderId, String name, String contactInformation) {
        this.bidderId = bidderId;
        this.name = name;
        this.contactInformation = contactInformation;
        this.participating = true;
    }

    @Override
    public void update(Auction auction) {
        System.out.println("\n[Bidder Notification]");

        System.out.println("Hello " + name + "!");
        System.out.println("Product: " + auction.getProductName());
        System.out.println("Current Highest Bid: $" + auction.getCurrentHighestBid());

        if (auction.getCurrentHighestBidder() != null) {
            System.out.println("Current Highest Bidder: "+ auction.getCurrentHighestBidder().getName());
        }

        System.out.println("Auction Status: " + (auction.isActive() ? "ACTIVE" : "CLOSED"));
    }

    public String getBidderId() {
        return bidderId;
    }

    public String getName() {
        return name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public boolean isParticipating() {
        return participating;
    }

    public void setParticipating(boolean participating) {
        this.participating = participating;
    }
}
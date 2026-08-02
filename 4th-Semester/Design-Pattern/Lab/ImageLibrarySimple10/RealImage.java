public class RealImage implements Image {
    private final ImageMetadata metadata;

    public RealImage(ImageMetadata metadata) {
        this.metadata = metadata;
        System.out.println("  Loading '" + metadata.getName() + "' from disk...");
    }

    @Override
    public void display(User user) {
        System.out.println("  Showing '" + metadata.getName() + "' to " + user);
    }

    @Override
    public String getInfo() {
        return metadata.describe();
    }
}

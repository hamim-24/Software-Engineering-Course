public class ImageProxy implements Image {
    private final ImageMetadata metadata;
    private RealImage realImage;

    public ImageProxy(ImageMetadata metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return metadata.getName();
    }

    @Override
    public void display(User user) {
        if (!canAccess(user)) {
            System.out.println("  DENIED: " + user + " cannot view '" + metadata.getName()
                    + "' (" + metadata.getPrivacyLevel() + ")");
            return;
        }
        if (realImage == null) {
            realImage = new RealImage(metadata);
        } else {
            System.out.println("  Using cached copy of '" + metadata.getName() + "'");
        }
        realImage.display(user);
    }

    @Override
    public String getInfo() {
        return metadata.describe();
    }

    private boolean canAccess(User user) {
        switch (metadata.getPrivacyLevel()) {
            case PUBLIC:     return true;
            case PRIVATE:    return user.isAdmin();
            case LOCKED:     return user.isAdmin() || user.knowsCode(metadata.getUnlockCode());
            case RESTRICTED: return user.isAdmin();
            default:         return false;
        }
    }
}

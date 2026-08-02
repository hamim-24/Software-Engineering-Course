import java.util.List;

public class ImageMetadata {
    private final String name;
    private final List<ImageTag> tags;
    private final ImageCategory category;
    private final PrivacyLevel privacyLevel;
    private final String unlockCode;

    public ImageMetadata(String name, List<ImageTag> tags, ImageCategory category, PrivacyLevel privacyLevel) {
        this(name, tags, category, privacyLevel, null);
    }

    public ImageMetadata(String name, List<ImageTag> tags, ImageCategory category,
                          PrivacyLevel privacyLevel, String unlockCode) {
        this.name = name;
        this.tags = tags;
        this.category = category;
        this.privacyLevel = privacyLevel;
        this.unlockCode = unlockCode;
    }

    public String getName() { return name; }
    public PrivacyLevel getPrivacyLevel() { return privacyLevel; }
    public String getUnlockCode() { return unlockCode; }

    public String describe() {
        return name + " | category=" + category + " | privacy=" + privacyLevel + " | tags=" + tags;
    }
}

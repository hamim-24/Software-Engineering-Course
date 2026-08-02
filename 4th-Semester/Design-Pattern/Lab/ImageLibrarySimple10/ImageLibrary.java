import java.util.ArrayList;
import java.util.List;

public class ImageLibrary {
    private final List<ImageProxy> images = new ArrayList<>();

    public ImageLibrary() {
        images.add(new ImageProxy(new ImageMetadata(
                "sunset.png", List.of(new ImageTag("nature")), ImageCategory.PUBLIC, PrivacyLevel.PUBLIC)));

        images.add(new ImageProxy(new ImageMetadata(
                "family.png", List.of(new ImageTag("personal")), ImageCategory.PERSONAL, PrivacyLevel.PRIVATE)));

        images.add(new ImageProxy(new ImageMetadata(
                "diary.png", List.of(new ImageTag("sensitive")), ImageCategory.PERSONAL, PrivacyLevel.LOCKED, "1234")));

        images.add(new ImageProxy(new ImageMetadata(
                "salary.png", List.of(new ImageTag("sensitive")), ImageCategory.RESTRICTED, PrivacyLevel.RESTRICTED)));
    }

    public Image getImage(String name) {
        return images.stream()
                .filter(img -> img.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}

public class ImageTag {
    private final String label;

    public ImageTag(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "#" + label;
    }
}

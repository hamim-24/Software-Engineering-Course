public abstract class FileSystemComponent {

    protected String name;
    protected FileSystemComponent parent;

    public FileSystemComponent(String name) {
        this.name = name;
        this.parent = null;
    }
    public String getName() {
        return name;
    }
    public abstract long getSize();
    public abstract String getInfo();
    public abstract boolean isFolder();

    public String getPath() {
        if (parent == null) return name;
        String parentPath = parent.getPath();
        return parentPath.equals("Root") ? name : parentPath + " / " + name;
    }

    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException(
            "'" + name + "' is a file — cannot add children to it.");
    }

    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException(
            "'" + name + "' is a file — cannot remove children from it.");
    }

    public java.util.List<FileSystemComponent> getChildren() {
        throw new UnsupportedOperationException(
            "'" + name + "' is a file — it has no children.");
    }

    public static String formatSize(long bytes) {
        if (bytes >= 1_048_576) return String.format("%.1f MB", bytes / 1_048_576.0);
        if (bytes >= 1_024)     return String.format("%d KB", bytes / 1_024);
        return bytes + " B";
    }

    @Override
    public String toString() {
        return name;
    }
}
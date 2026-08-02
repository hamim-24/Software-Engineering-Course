public class File extends FileSystemComponent {

    private final String extension;
    private final long size;

    public File(String name, String extension, long sizeInBytes) {
        super(name);
        this.extension = extension;
        this.size = sizeInBytes;
    }
    public File(String name, String extension) {
        this(name, extension, (long)(Math.random() * 4000 + 200));
    }
    @Override
    public long getSize() {
        return size;
    }

    @Override
    public boolean isFolder() {
        return false;
    }

    @Override
    public String getInfo() {
        return String.format(
            "%-12s  %-6s  %-6s  Size: %s  Path: %s",
            "[FILE]", "." + extension, name,
            formatSize(size), getPath()
        );
    }
    public String getExtension() {
        return extension;
    }
}
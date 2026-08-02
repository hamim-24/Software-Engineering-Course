import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemComponent {

    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }
    @Override
    public void add(FileSystemComponent component) {
        component.parent = this;
        children.add(component);
    }

    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
        component.parent = null;
    }

    @Override
    public List<FileSystemComponent> getChildren() {
        return new ArrayList<>(children);
    }
    @Override
    public long getSize() {
        return children.stream().mapToLong(FileSystemComponent::getSize).sum();
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public String getInfo() {
        return String.format(
            "%-12s  %-6s  %s  Items: %d  Size: %s  Path: %s",
            "[FOLDER]", "", name,
            children.size(), formatSize(getSize()), getPath()
        );
    }
    public int getFileCount() {
        int count = 0;
        for (FileSystemComponent c : children) {
            if (c.isFolder()) {
                count += ((Folder) c).getFileCount();
            } else {
                count++;
            }
        }
        return count;
    }
    public int getFolderCount() {
        int count = 0;
        for (FileSystemComponent c : children) {
            if (c.isFolder()) {
                count++;
                count += ((Folder) c).getFolderCount();
            }
        }
        return count;
    }

    public void printTree(String prefix, boolean isLastChild) {
        String connector = isLastChild ? "" : " ";
        String icon = "(folder)=>) ";
        System.out.println(prefix + connector + icon + name
            + "  (" + formatSize(getSize()) + ")");

        String childPrefix = prefix + (isLastChild ? "    " : "   ");
        for (int i = 0; i < children.size(); i++) {
            boolean last = (i == children.size() - 1);
            FileSystemComponent child = children.get(i);
            if (child.isFolder()) {
                ((Folder) child).printTree(childPrefix, last);
            } else {
                String cc = last ? " " : " ";
                File f = (File) child;
                System.out.println(childPrefix + cc + "=>(file) " + child.getName()
                    + "  [." + f.getExtension() + "]"
                    + "  (" + formatSize(child.getSize()) + ")");
            }
        }
    }
}
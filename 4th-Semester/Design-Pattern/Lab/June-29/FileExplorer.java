import java.util.List;

public class FileExplorer {

    private final Folder root;
    private Folder current;


    public FileExplorer(Folder root) {
        this.root = root;
        this.current = root;

    }

    public void navigateTo(Folder folder) {
        current = folder;
        System.out.println(">> Navigated to: " + current.getPath());
    }

    public void navigateToRoot() {
        current = root;
        System.out.println(">> Navigated to root.");
    }

    public Folder getCurrent() {
        return current;
    }

    public Folder getRoot() {
        return root;
    }
    public Folder createFolder(String name) {
        Folder folder = new Folder(name);
        current.add(folder);
        System.out.println(">> Created folder: " + folder.getPath());
        return folder;
    }

    public File createFile(String name, String extension, long sizeBytes) {
        File file = new File(name, extension, sizeBytes);
        current.add(file);
        System.out.println(">> Created file:   " + file.getPath()
            + "  (" + FileSystemComponent.formatSize(sizeBytes) + ")");
        return file;
    }

    public void delete(FileSystemComponent component) {
        if (component.parent == null) {
            System.out.println("!! Cannot delete root.");
            return;
        }
        String path = component.getPath();
        ((Folder) component.parent).remove(component);
        System.out.println(">> Deleted: " + path);
    }
    public void move(FileSystemComponent component, Folder destination) {
        if (component.parent == null) {
            System.out.println("!! Cannot move root.");
            return;
        }
        String oldPath = component.getPath();
        ((Folder) component.parent).remove(component);
        destination.add(component);
        System.out.println(">> Moved: " + oldPath + " → " + component.getPath());
    }
    public void printTree() {
        System.out.println("\n════════════ Directory Tree ════════════");
        System.out.println("=>(folder) " + root.getName()
            + "  (" + FileSystemComponent.formatSize(root.getSize()) + ")");
        for (int i = 0; i < root.getChildren().size(); i++) {
            boolean last = (i == root.getChildren().size() - 1);
            FileSystemComponent child = root.getChildren().get(i);
            if (child.isFolder()) {
                ((Folder) child).printTree("", last);
            } else {
                String c = last ? " " : " ";
                File f = (File) child;
                System.out.println(c + "=>(file)" + child.getName()
                    + "  [." + f.getExtension() + "]"
                    + "  (" + FileSystemComponent.formatSize(child.getSize()) + ")");
            }
        }
        System.out.println("════════════════════════════════════════\n");
    }

    public void listCurrent() {
        System.out.println("\n── Contents of " + current.getPath() + " ──");
        List<FileSystemComponent> items = current.getChildren();
        if (items.isEmpty()) {
            System.out.println("   (empty)");
            return;
        }
        for (FileSystemComponent item : items) {
            System.out.println("   " + item.getInfo());
        }
        System.out.println("   " + items.size() + " item(s)  |  "
            + FileSystemComponent.formatSize(current.getSize()) + " total");
    }

    public void printStats() {
        System.out.println("\n════════════ Summary Statistics ════════════");
        System.out.printf("  Total files   : %d%n", root.getFileCount());
        System.out.printf("  Total folders : %d%n", root.getFolderCount());
        System.out.printf("  Total size    : %s%n",
            FileSystemComponent.formatSize(root.getSize()));
        System.out.println("════════════════════════════════════════════\n");
    }
    
}
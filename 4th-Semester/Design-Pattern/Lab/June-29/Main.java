public class Main {

    public static void main(String[] args) {

        Folder root = new Folder("Root");
        FileExplorer explorer = new FileExplorer(root);

        Folder src = new Folder("src");
        root.add(src);

        Folder components = new Folder("components");
        src.add(components);
        components.add(new File("Button.jsx",   "jsx",  620));
        components.add(new File("Modal.tsx",    "tsx", 1200));
        components.add(new File("Table.jsx",    "jsx", 2100));

        src.add(new File("main.java",  "java", 3200));
        src.add(new File("App.java",   "java", 1800));
        src.add(new File("utils.py",   "py",    900));

        Folder assets = new Folder("assets");
        root.add(assets);

        Folder images = new Folder("images");
        assets.add(images);
        images.add(new File("logo.svg",    "svg",   4200));
        images.add(new File("banner.png",  "png",  85000));
        images.add(new File("avatar.jpg",  "jpg",  23000));

        assets.add(new File("styles.css",  "css",  8400));
        assets.add(new File("theme.scss",  "scss", 3100));

        Folder docs = new Folder("docs");
        root.add(docs);
        docs.add(new File("README.md",       "md",      1500));
        docs.add(new File("API.md",          "md",      4200));
        docs.add(new File("architecture.pdf","pdf",   124000));

        root.add(new File("package.json",  "json",  800));
        root.add(new File("tsconfig.json", "json",  500));
        root.add(new File(".gitignore",    "txt",   220));

        System.out.println("\n──── directory tree ────");
        explorer.navigateToRoot();
        explorer.printTree();
        //explorer.printStats();

        System.out.println("\n──── Delete operation ────");
        FileSystemComponent gitignore = findByName(root, ".gitignore");
        if (gitignore != null) explorer.delete(gitignore);

        System.out.println("\n──── Final directory tree ────");
        explorer.navigateToRoot();
        explorer.printTree();
        //explorer.printStats();
    }

    private static FileSystemComponent findByName(Folder folder, String name) {
        for (FileSystemComponent child : folder.getChildren()) {
            if (child.getName().equals(name)) return child;
            if (child.isFolder()) {
                FileSystemComponent found = findByName((Folder) child, name);
                if (found != null) return found;
            }
        }
        return null;
    }
}
import java.util.*;

public class HistoryManager {

    private final TextEditor editor;

    private final Stack<EditorMemento> undoStack;
    private final Stack<EditorMemento> redoStack;

    private final List<Version> versions;

    public HistoryManager(TextEditor editor) {
        this.editor = editor;

        undoStack = new Stack<>();
        redoStack = new Stack<>();
        versions = new ArrayList<>();
    }

    public void saveState() {
        undoStack.push(editor.createMemento());
        redoStack.clear();
    }

    public void undo() {

        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        redoStack.push(editor.createMemento());

        EditorMemento previousState = undoStack.pop();

        editor.restore(previousState);
    }

    public void redo() {

        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }
        undoStack.push(editor.createMemento());
        EditorMemento nextState = redoStack.pop();

        editor.restore(nextState);
    }

    public void saveVersion(String versionName) {
        for (Version version : versions) {
            if (version.getVersionName().equalsIgnoreCase(versionName)) {
                System.out.println("Error: Version '" + versionName + "' already exists.");
                return;
            }
        }

        EditorMemento memento = editor.createMemento();

        Version version = new Version(versionName, memento);

        versions.add(version);

        System.out.println("Version '" + versionName + "' saved successfully.");
    }

    public void rollbackToVersion(String versionName) {

        for (Version version : versions) {

            if (version.getVersionName().equalsIgnoreCase(versionName)) {
                undoStack.push(editor.createMemento());
                redoStack.clear();

                editor.restore(version.getMemento());

                System.out.println("Rolled back to version '" + versionName + "'.");

                return;
            }
        }

        System.out.println("Error: Version '" + versionName + "' not found.");
    }

    public void listVersions() {
        if (versions.isEmpty()) {
            System.out.println("No saved versions.");
            return;
        }

        System.out.println("Saved Versions:");

        for (int i = 0; i < versions.size(); i++) {
            System.out.println((i + 1) + ". " + versions.get(i).getVersionName());
        }
    }
}